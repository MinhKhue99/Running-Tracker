package com.minhkhue.runningtracker.repository

import androidx.lifecycle.LiveData
import com.minhkhue.runningtracker.model.local.Meal
import com.minhkhue.runningtracker.model.remote.CategoryResponse
import com.minhkhue.runningtracker.model.remote.MealResponse
import retrofit2.Response

interface IRecipeRepository {
    suspend fun getRecipeDetails(idMeal:Int): Response<MealResponse>
    suspend fun getRecommendMeals():Response<MealResponse>
    suspend fun getAllCategories():Response<CategoryResponse>
    suspend fun getAllMealByCategory(mealId: String):Response<MealResponse>
    suspend fun isFavorite(mealId: Int) : Int
    suspend fun setFavorite(meal : Meal)
    suspend fun setFavorite(mealId: Int, isFavorite: Int)
    suspend fun getFavoriteMeals(): LiveData<List<Meal>>
}