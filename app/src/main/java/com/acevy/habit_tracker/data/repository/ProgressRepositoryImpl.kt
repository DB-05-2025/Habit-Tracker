package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import com.acevy.habit_tracker.data.local.room.dao.UserProgressDao
import com.acevy.habit_tracker.domain.repository.ProgressRepository
import kotlinx.coroutines.flow.Flow

class ProgressRepositoryImpl(
    private val dao: UserProgressDao
) : ProgressRepository {

    override suspend fun upsertProgress(data: UserProgressEntity) {
        dao.upsertProgress(data)
    }

    override fun getProgress(): Flow<UserProgressEntity?> {
        return dao.getProgress()
    }
}
