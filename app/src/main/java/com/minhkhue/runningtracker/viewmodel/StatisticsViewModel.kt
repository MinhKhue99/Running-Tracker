package com.minhkhue.runningtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.minhkhue.runningtracker.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    val totalTimeRun = mainRepository.getTotalTimeInMillis()
    val totalDistance = mainRepository.getTotalDistances()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()
    val runsSortedByDate = mainRepository.getAllRunSortByDate()
}