package com.acevy.habit_tracker.data.local.dao.habitstack

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.acevy.habit_tracker.data.model.habitstack.HabitStackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitStackDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertHabitStack(habitStack: HabitStackEntity): Long

    @Update
    suspend fun updateHabitStack(habitStack: HabitStackEntity)

    @Delete
    suspend fun deleteHabitStack(habitStack: HabitStackEntity)

    @Query("SELECT * FROM habit_stack WHERE userId = :userId ORDER BY createdAt DESC")
    fun getHabitStacksByUser(userId: Long): Flow<List<HabitStackEntity>>
}