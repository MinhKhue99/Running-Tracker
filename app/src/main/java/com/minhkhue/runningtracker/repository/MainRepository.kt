package com.minhkhue.runningtracker.repository

import androidx.lifecycle.LiveData
import com.minhkhue.runningtracker.data.local.database.RunDao
import com.minhkhue.runningtracker.model.Run
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDao: RunDao,
) {
    private val ioDispatcher = Dispatchers.IO

    suspend fun insertRun(run: Run) {
        withContext(ioDispatcher) {
            runDao.insertRun(run)
        }
    }

    suspend fun deleteRun(run: Run) {
        withContext(ioDispatcher) {
            runDao.deleteRun(run)
        }
    }

    fun getAllRunSortByDate(): LiveData<List<Run>> = runDao.getAllRunSortByDate()

    fun getAllRunSortByTimeInMillis(): LiveData<List<Run>> = runDao.getAllRunSortByTimeInMillis()


    fun getAllRunSortByDistances(): LiveData<List<Run>> = runDao.getAllRunSortByDistances()


    fun getAllRunSortByAvgSpeed(): LiveData<List<Run>> = runDao.getAllRunSortByAvgSpeed()

    fun getAllRunSortByCaloriesBurned(): LiveData<List<Run>> =
        runDao.getAllRunSortByCaloriesBurned()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistances() = runDao.getTotalDistances()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()
}