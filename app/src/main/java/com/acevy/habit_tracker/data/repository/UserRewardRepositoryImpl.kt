package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.local.dao.UserRewardDao
import com.acevy.habit_tracker.data.mapper.toDomain
import com.acevy.habit_tracker.data.mapper.toEntity
import com.acevy.habit_tracker.domain.model.UserReward
import com.acevy.habit_tracker.domain.repository.UserRewardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRewardRepositoryImpl(
    private val dao: UserRewardDao,
) : UserRewardRepository {

    override suspend fun insertUserReward(reward: UserReward) {
        dao.insertUserReward(reward.toEntity())
    }

    override fun getRewardsByUser(userId: Long): Flow<List<UserReward>> {
        return dao.getRewardsByUser(userId).map { list -> list.map { it.toDomain() } }
    }
}