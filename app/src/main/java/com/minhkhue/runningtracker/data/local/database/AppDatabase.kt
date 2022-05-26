package com.minhkhue.runningtracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.minhkhue.runningtracker.model.local.Meal
import com.minhkhue.runningtracker.model.local.Run
import com.minhkhue.runningtracker.model.local.User

@Database(
    entities = [Run::class,User::class, Meal::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRunDao(): RunDao
    abstract fun getUserDao():UserDao
    abstract fun getMealDao():MealDao
}