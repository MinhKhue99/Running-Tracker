package com.minhkhue.runningtracker.data.remote

import com.minhkhue.runningtracker.model.remote.CategoryResponse
import com.minhkhue.runningtracker.model.remote.MealResponse
import com.minhkhue.runningtracker.model.remote.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class APIHelperImpl @Inject constructor(
    private val apiService: APIService,
    private val newsAPIService: NewsAPIService
) : APIHelper {
    override suspend fun getRecommendMeals(): Response<MealResponse> =
        apiService.getRecommendMeals()

    override suspend fun getAllCategories(): Response<CategoryResponse> =
        apiService.getAllCategories()

    override suspend fun getAllMealByCategory(searchQuery: String): Response<MealResponse> =
        apiService.getAllMealByCategory(searchQuery)

    override suspend fun getRecipeDetails(idMeal: Int): Response<MealResponse> =
        apiService.getRecipeDetails(idMeal)

    override suspend fun getNews(): Response<NewsResponse> = newsAPIService.getNews()
}