package com.acevy.habit_tracker.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitStackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStack(stack: HabitStackEntity)

    @Update
    suspend fun updateStack(stack: HabitStackEntity)

    @Delete
    suspend fun deleteStack(stack: HabitStackEntity)

    @Query("SELECT * FROM habit_stacks ORDER BY createdAt DESC")
    fun getAllStacks(): Flow<List<HabitStackEntity>>

    @Query("SELECT * FROM habit_stacks WHERE id = :id LIMIT 1")
//    fun getStackById(id: Int): Flow<HabitStackEntity>
    fun getStackById(id: Int): Flow<HabitStackEntity?>
}

