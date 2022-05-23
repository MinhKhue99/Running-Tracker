package com.minhkhue.runningtracker.repository

import com.minhkhue.runningtracker.data.remote.APIHelper
import com.minhkhue.runningtracker.model.remote.MealResponse
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val apiHelper: APIHelper) : IRecipeRepository {
    suspend fun getRecommendMeals() = apiHelper.getRecommendMeals()

    suspend fun getAllCategories() = apiHelper.getAllCategories()

    suspend fun getAllMealByCategory(searchQuery: String) =
        apiHelper.getAllMealByCategory(searchQuery)

    override suspend fun getRecipeDetails(idMeal: Int): Response<MealResponse> {
        val apiResponse = apiHelper.getRecipeDetails(idMeal)
        return if (apiResponse.isSuccessful) {
            val mealData = (apiResponse.body() as MealResponse).meals.toMutableList()
            Response.success(MealResponse(mealData))
        } else {
            Response.error(apiResponse.errorBody(), null)
        }
    }
}