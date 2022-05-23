package com.minhkhue.runningtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.minhkhue.runningtracker.repository.RunRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(private val runRepository: RunRepository) :
    ViewModel() {
    val totalTimeRun = runRepository.getTotalTimeInMillis()
    val totalDistance = runRepository.getTotalDistances()
    val totalCaloriesBurned = runRepository.getTotalCaloriesBurned()
    val totalAvgSpeed = runRepository.getTotalAvgSpeed()
    val runsSortedByDate = runRepository.getAllRunSortByDate()
}