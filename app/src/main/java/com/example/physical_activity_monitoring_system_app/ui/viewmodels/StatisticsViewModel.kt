package com.example.physical_activity_monitoring_system_app.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.physical_activity_monitoring_system_app.repositories.MainRepository

class StatisticsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel(){

    val totalTimeRun = mainRepository.getTotalTimeInMillisec()
    val totalDistance = mainRepository.getTotalDistance()
    val totalBurnedCalories = mainRepository.getTotalBurnedCalories()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()

    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
}