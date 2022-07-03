package com.example.physical_activity_monitoring_system_app.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physical_activity_monitoring_system_app.db.Run
import com.example.physical_activity_monitoring_system_app.other.SortType
import com.example.physical_activity_monitoring_system_app.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel(){

    private val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
    private val runsSortedByBurnedCalories = mainRepository.getAllRunsSortedByBurnedCalories()
    private val runsSortedByDistance = mainRepository.getAllRunsSortedByDistance()
    private val runsSortedByTimeInMillisec = mainRepository.getAllRunsSortedByTimeInMillisec()
    private val runsSortedByAvgSpeed = mainRepository.getAllRunsSortedByAvgSpeed()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runsSortedByDate) { result ->
            if(sortType == SortType.DATE) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByBurnedCalories) { result ->
            if(sortType == SortType.BURNED_CALORIES) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByDistance) { result ->
            if(sortType == SortType.DISTANCE) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByTimeInMillisec) { result ->
            if(sortType == SortType.RUNNING_TIME) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByAvgSpeed) { result ->
            if(sortType == SortType.AVG_SPEED) {
                result?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType) {
        SortType.DATE -> runsSortedByDate.value?.let { runs.value = it}
        SortType.RUNNING_TIME -> runsSortedByTimeInMillisec.value?.let { runs.value = it}
        SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it}
        SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it}
        SortType.BURNED_CALORIES -> runsSortedByBurnedCalories.value?.let { runs.value = it}
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

    fun deleteRun(run: Run) = viewModelScope.launch {
        mainRepository.deleteRun(run)
    }
}