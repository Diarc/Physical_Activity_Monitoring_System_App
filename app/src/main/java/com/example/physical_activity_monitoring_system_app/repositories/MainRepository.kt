package com.example.physical_activity_monitoring_system_app.repositories

import com.example.physical_activity_monitoring_system_app.db.Run
import com.example.physical_activity_monitoring_system_app.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDAO
){
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()

    fun getAllRunsSortedByTimeInMillisec() = runDao.getAllRunsSortedByTimeInMillisec()

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByBurnedCalories() = runDao.getAllRunsSortedByBurnedCalories()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalBurnedCalories() = runDao.getTotalBurnedCalories()

    fun getTotalTimeInMillisec() = runDao.getTotalTimeInMilisec()

}