package com.acevy.habit_tracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.model.RewardTypeEntity
import com.acevy.habit_tracker.data.model.UserRewardEntity
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "habit-db"
        )
            .fallbackToDestructiveMigration() // opsional, jika belum pakai migration
            .build()

        lifecycleScope.launch {
            val rewardTypeDao = db.rewardTypeDao()
            val userRewardDao = db.userRewardDao()

            // Insert RewardType "XP" jika belum ada
            val existingTypes = rewardTypeDao.getAll()
            val xpTypeId = existingTypes.firstOrNull { it.name == "XP" }?.id ?: run {
                rewardTypeDao.insertRewardType(
                    RewardTypeEntity(
                        name = "XP",
                        description = "Experience Points"
                    )
                )
                rewardTypeDao.getAll().first { it.name == "XP" }.id
            }

            // Insert reward ke userId = 1
            userRewardDao.insertUserReward(
                UserRewardEntity(
                    userId = 1L,
                    rewardTypeId = xpTypeId,
                    amount = 50,
                    source = "habit_completed"
                )
            )

            // Fetch & log reward milik userId = 1
            val rewards = userRewardDao.getRewardsByUser(1L)
            rewards.forEach {
                Log.d(
                    "REWARD_TEST",
                    "User earned ${it.amount} from ${it.source}, typeId=${it.rewardTypeId}"
                )
            }
        }

        setContent {
            HabitTrackerTheme {

            }
        }
    }
}