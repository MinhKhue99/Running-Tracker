package com.minhkhue.runningtracker.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.minhkhue.runningtracker.model.local.Run

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRunDao(): RunDao
}