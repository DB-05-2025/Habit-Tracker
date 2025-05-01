package com.acevy.habit_tracker.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.data.local.entity.HabitStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: HabitLogEntity)

    @Query("SELECT * FROM habit_logs WHERE habitId = :habitId AND date = :date LIMIT 1")
    suspend fun getLogByHabitAndDate(habitId: Int, date: String): HabitLogEntity?

    @Query("SELECT * FROM habit_logs WHERE date = :date AND status = :status")
    fun getLogsByDateAndStatus(date: String, status: HabitStatus): Flow<List<HabitLogEntity>>

    @Query("SELECT * FROM habit_logs WHERE date = :date")
    fun getLogsByDate(date: String): Flow<List<HabitLogEntity>>

    @Query("DELETE FROM habit_logs WHERE habitId = :habitId")
    suspend fun deleteLogsByHabitId(habitId: Int)

    @Query("DELETE FROM habit_logs WHERE date != :today")
    suspend fun clearLogsNotToday(today: String)

    @Query("SELECT * FROM habit_logs WHERE date = :date")
    suspend fun getLogsByDateOnce(date: String): List<HabitLogEntity>

    @Update
    suspend fun updateLog(log: HabitLogEntity)

}
