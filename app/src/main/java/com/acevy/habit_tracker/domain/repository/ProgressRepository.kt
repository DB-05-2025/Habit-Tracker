package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import kotlinx.coroutines.flow.Flow

interface ProgressRepository {
    suspend fun upsertProgress(data: UserProgressEntity)
    fun getProgress(): Flow<UserProgressEntity?>
}
