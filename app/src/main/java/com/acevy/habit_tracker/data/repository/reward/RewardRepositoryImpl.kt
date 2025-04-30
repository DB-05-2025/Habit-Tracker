package com.acevy.habit_tracker.data.repository.reward

import com.acevy.habit_tracker.data.local.dao.RewardTypeDao
import com.acevy.habit_tracker.data.local.dao.reward.UserRewardDao
import com.acevy.habit_tracker.data.local.dao.userlevel.UserLevelDao
import com.acevy.habit_tracker.data.model.RewardTypeEntity
import com.acevy.habit_tracker.data.model.reward.UserRewardEntity
import com.acevy.habit_tracker.data.model.userlevel.UserLevelEntity
import com.acevy.habit_tracker.domain.model.RewardType
import com.acevy.habit_tracker.domain.model.userlevel.UserLevel
import com.acevy.habit_tracker.domain.model.reward.UserReward
import com.acevy.habit_tracker.domain.repository.reward.RewardRepository

class RewardRepositoryImpl(
    private val rewardTypeDao: RewardTypeDao,
    private val userRewardDao: UserRewardDao,
    private val userLevelDao: UserLevelDao,
) : RewardRepository {

    override suspend fun addUserReward(reward: UserReward) {
        userRewardDao.insertUserReward(reward.toEntity())
    }

    override suspend fun getRewardsByUser(userId: Long): List<UserReward> {
        return userRewardDao.getRewardsByUser(userId).map { it.toDomain() }
    }

    override suspend fun getUserLevel(userId: Long): UserLevel? {
        return userLevelDao.getByUserId(userId)?.toDomain()
    }

    override suspend fun upsertUserLevel(level: UserLevel) {
        userLevelDao.upsertUserLevel(level.toEntity())
    }

    override suspend fun getRewardTypeByName(name: String): RewardType? {
        return rewardTypeDao.getAll().firstOrNull { it.name == name }?.toDomain()
    }

    override suspend fun insertRewardType(type: RewardType) {
        rewardTypeDao.insertRewardType(type.toEntity())
    }

    // Mapping Extensions
    private fun UserReward.toEntity() = UserRewardEntity(
        id, userId, rewardTypeId, amount, source, earnedAt
    )

    private fun UserRewardEntity.toDomain() = UserReward(
        id, userId, rewardTypeId, amount, source, earnedAt
    )

    private fun RewardType.toEntity() = RewardTypeEntity(
        id, name, description
    )

    private fun RewardTypeEntity.toDomain() = RewardType(
        id, name, description
    )

    private fun UserLevel.toEntity() = UserLevelEntity(
        id, userId, level, currentXp, updatedAt
    )

    private fun UserLevelEntity.toDomain() = UserLevel(
        id, userId, level, currentXp, updatedAt
    )
}