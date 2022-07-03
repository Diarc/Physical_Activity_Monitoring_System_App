package com.example.physical_activity_monitoring_system_app.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY timeInMilisec DESC")
    fun getAllRunsSortedByTimeInMillisec(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY burnedCalories DESC")
    fun getAllRunsSortedByBurnedCalories(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeedInKMH DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>>

    @Query("SELECT Sum(timeInMilisec) FROM running_table")
    fun getTotalTimeInMilisec(): LiveData<Long>

    @Query("SELECT Sum(burnedCalories) FROM running_table")
    fun getTotalBurnedCalories(): LiveData<Int>

    @Query("SELECT Sum(distanceInMeters) FROM running_table")
    fun getTotalDistance(): LiveData<Int>

    @Query("SELECT Sum(avgSpeedInKMH) FROM running_table")
    fun getTotalAvgSpeed(): LiveData<Float>
}