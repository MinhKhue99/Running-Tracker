package com.minhkhue.runningtracker.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "meals")
data class Meal(
    @PrimaryKey
    @Json(name = "idMeal")
    val idMeal: Int,
    @Json(name = "strMeal")
    val mealName: String,
    @Json(name = "strMealThumb")
    val mealImageUrl: String,
    val category: String?,
    val isFavorite: Int = 0
)