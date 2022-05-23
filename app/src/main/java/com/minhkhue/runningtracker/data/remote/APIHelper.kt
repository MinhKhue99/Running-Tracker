package com.minhkhue.runningtracker.data.remote

import com.minhkhue.runningtracker.model.remote.CategoryResponse
import com.minhkhue.runningtracker.model.remote.MealResponse
import com.minhkhue.runningtracker.model.remote.NewsResponse
import retrofit2.Response

interface APIHelper {
    suspend fun getRecommendMeals(): Response<MealResponse>

    suspend fun getAllCategories(): Response<CategoryResponse>

    suspend fun getAllMealByCategory(
        searchQuery: String
    ): Response<MealResponse>

    suspend fun getRecipeDetails(idMeal: Int): Response<MealResponse>

    suspend fun getNews() :Response<NewsResponse>
}