package com.minhkhue.runningtracker.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.minhkhue.runningtracker.model.local.Run

@Dao
interface RunDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timeStamp DESC")
    fun getAllRunSortByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeedInKM DESC")
    fun getAllRunSortByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distancesInMeter DESC")
    fun getAllRunSortByDistances(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY timeInMillis DESC")
    fun getAllRunSortByTimeInMillis(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunSortByCaloriesBurned(): LiveData<List<Run>>

    @Query("SELECT SUM(timeInMillis) FROM running_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT SUM(distancesInMeter) FROM running_table")
    fun getTotalDistances(): LiveData<Int>

    @Query("SELECT SUM(avgSpeedInKM) FROM running_table")
    fun getTotalAvgSpeed(): LiveData<Float>

}