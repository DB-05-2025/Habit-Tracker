package com.acevy.habit_tracker.data.local.dao.habit

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.acevy.habit_tracker.data.model.habit.HabitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertHabit(habit: HabitEntity): Long

    @Update
    suspend fun updateHabit(habit: HabitEntity)

    @Delete
    suspend fun deleteHabit(habit: HabitEntity)

    @Query("SELECT * FROM habit WHERE userId = :userId ORDER BY createdAt DESC")
    fun getHabitsByUser(userId: Long): Flow<List<HabitEntity>>
}