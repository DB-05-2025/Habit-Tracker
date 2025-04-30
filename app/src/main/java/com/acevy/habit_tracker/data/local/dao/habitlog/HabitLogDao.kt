package com.acevy.habit_tracker.data.local.dao.habitlog

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.acevy.habit_tracker.data.model.habitlog.HabitLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitLogDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertHabitLog(log: HabitLogEntity): Long

    @Update
    suspend fun updateHabitLog(log: HabitLogEntity)

    @Delete
    suspend fun deleteHabitLog(log: HabitLogEntity)

    @Query("SELECT * FROM habit_log WHERE habitId = :habitId ORDER BY date DESC")
    fun getLogsByHabitId(habitId: Long): Flow<List<HabitLogEntity>>
}