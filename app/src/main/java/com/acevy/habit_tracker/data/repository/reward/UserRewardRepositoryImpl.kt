package com.acevy.habit_tracker.data.repository.reward

import com.acevy.habit_tracker.data.local.dao.reward.UserRewardDao
import com.acevy.habit_tracker.data.mapper.reward.toDomain
import com.acevy.habit_tracker.data.mapper.reward.toEntity
import com.acevy.habit_tracker.domain.model.reward.UserReward
import com.acevy.habit_tracker.domain.repository.reward.UserRewardRepository
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