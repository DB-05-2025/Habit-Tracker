package com.acevy.habit_tracker.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acevy.habit_tracker.data.model.HabitLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitLog(log: HabitLogEntity)

    @Query("SELECT * FROM habit_log WHERE habitId = :habitId ORDER BY date DESC")
    fun getLogsByHabit(habitId: Long): Flow<List<HabitLogEntity>>

    @Query("SELECT * FROM habit_log WHERE habitId = :habitId AND date = :date LIMIT 1")
    suspend fun getLogByDate(habitId: Long, date: String): HabitLogEntity?

    @Delete
    suspend fun deleteHabitLog(log: HabitLogEntity)
}