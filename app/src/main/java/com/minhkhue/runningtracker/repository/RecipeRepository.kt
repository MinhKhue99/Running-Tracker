package com.minhkhue.runningtracker.repository

import androidx.lifecycle.LiveData
import com.minhkhue.runningtracker.data.local.database.MealDao
import com.minhkhue.runningtracker.data.remote.APIHelper
import com.minhkhue.runningtracker.model.local.Meal
import com.minhkhue.runningtracker.model.remote.MealResponse
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiHelper: APIHelper,
    private val mealDao: MealDao
) : IRecipeRepository {

    override suspend fun getRecommendMeals() = apiHelper.getRecommendMeals()

    override suspend fun getAllCategories() = apiHelper.getAllCategories()

    override suspend fun getAllMealByCategory(mealId: String): Response<MealResponse> {
        return apiHelper.getAllMealByCategory(mealId)
    }

    override suspend fun getRecipeDetails(idMeal: Int): Response<MealResponse> {
        val apiResponse = apiHelper.getRecipeDetails(idMeal)
        return if (apiResponse.isSuccessful) {
            val mealData = (apiResponse.body() as MealResponse).meals.toMutableList()
            Response.success(MealResponse(mealData))
        } else {
            Response.error(apiResponse.errorBody(), null)
        }
    }

    override suspend fun isFavorite(mealId: Int): Int = mealDao.isFavorite(mealId)

    override suspend fun setFavorite(meal: Meal) = mealDao.setFavorite(meal)


    override suspend fun setFavorite(mealId: Int, isFavorite: Int) =
        mealDao.setFavorite(mealId, isFavorite)

    override suspend fun getFavoriteMeals(): LiveData<List<Meal>> = mealDao.getFavoriteMeals()
}