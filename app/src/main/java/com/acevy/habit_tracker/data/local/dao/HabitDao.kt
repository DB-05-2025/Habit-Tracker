package com.acevy.habit_tracker.data.local.dao

import androidx.room.*
import com.acevy.habit_tracker.data.model.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity): Long

    @Update
    suspend fun updateHabit(habit: HabitEntity)

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Query("SELECT * FROM habit WHERE userId = :userId ORDER BY createdAt DESC")
    fun getHabitsByUser(userId: Long): Flow<List<HabitEntity>>
}