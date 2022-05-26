package com.minhkhue.runningtracker.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.minhkhue.runningtracker.model.local.Meal

@Dao
interface MealDao {

    @Query("SELECT isFavorite FROM meals WHERE mealId = :mealId")
    fun isFavorite(mealId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavorite(meal: Meal)

    @Query("UPDATE meals SET isFavorite = :isFavorite WHERE mealId = :mealId")
    fun setFavorite(mealId: Int, isFavorite:Int)

    @Query("SELECT * FROM meals WHERE isFavorite = 1")
    fun getFavoriteMeals(): LiveData<List<Meal>>
}