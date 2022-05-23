package com.minhkhue.runningtracker.data.remote

import com.minhkhue.runningtracker.model.remote.CategoryResponse
import com.minhkhue.runningtracker.model.remote.MealResponse
import com.minhkhue.runningtracker.utils.Constant.CATEGORY_END_POINT
import com.minhkhue.runningtracker.utils.Constant.FILTER_END_POINT
import com.minhkhue.runningtracker.utils.Constant.RECOMMENDATION_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET(RECOMMENDATION_END_POINT)
    suspend fun getRecommendMeals(): Response<MealResponse>

    @GET(CATEGORY_END_POINT)
    suspend fun getAllCategories(): Response<CategoryResponse>

    @GET(FILTER_END_POINT)
    suspend fun getAllMealByCategory(
        @Query("c")
        searchQuery: String
    ): Response<MealResponse>

    @GET("lookup.php")
    suspend fun getRecipeDetails(@Query("i") mealId: Int): Response<MealResponse>

}