package com.acevy.habit_tracker.data.local.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.acevy.habit_tracker.data.model.user.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM user WHERE userId = :userId LIMIT 1")
    fun getUserById(userId: Long): Flow<UserEntity?>

    @Update
    suspend fun updateUser(user: UserEntity)
}