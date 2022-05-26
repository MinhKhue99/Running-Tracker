package com.minhkhue.runningtracker.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.minhkhue.runningtracker.data.local.database.AppDatabase
import com.minhkhue.runningtracker.utils.Constant
import com.minhkhue.runningtracker.utils.Constant.KEY_FIRST_TIME_TOGGLE
import com.minhkhue.runningtracker.utils.Constant.KEY_NAME
import com.minhkhue.runningtracker.utils.Constant.KEY_WEIGHT
import com.minhkhue.runningtracker.utils.Constant.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        Constant.APP_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRunDao(db: AppDatabase) = db.getRunDao()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.getUserDao()

    @Provides
    @Singleton
    fun provideMealDao(db: AppDatabase) = db.getMealDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) =
        sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE, true)
}