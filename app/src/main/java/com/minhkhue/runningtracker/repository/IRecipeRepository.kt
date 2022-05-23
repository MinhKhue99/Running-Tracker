package com.minhkhue.runningtracker.repository

import com.minhkhue.runningtracker.model.remote.MealResponse
import retrofit2.Response

interface IRecipeRepository {
    suspend fun getRecipeDetails(idMeal:Int): Response<MealResponse>
}