package com.acevy.habit_tracker.di

import android.content.Context
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.habit.HabitRepositoryImpl
import com.acevy.habit_tracker.data.repository.habitlog.HabitLogRepositoryImpl
import com.acevy.habit_tracker.data.repository.habitstack.HabitStackRepositoryImpl
import com.acevy.habit_tracker.data.repository.notificationlog.NotificationLogRepositoryImpl
import com.acevy.habit_tracker.data.repository.reward.UserRewardRepositoryImpl
import com.acevy.habit_tracker.data.repository.user.UserRepositoryImpl
import com.acevy.habit_tracker.data.repository.userlevel.UserLevelRepositoryImpl
import com.acevy.habit_tracker.di.container.HabitLogUseCases
import com.acevy.habit_tracker.di.container.HabitStackUseCases
import com.acevy.habit_tracker.di.container.HabitUseCases
import com.acevy.habit_tracker.di.container.NotificationLogUseCases
import com.acevy.habit_tracker.di.container.UserLevelUseCases
import com.acevy.habit_tracker.di.container.UserRewardUseCases
import com.acevy.habit_tracker.di.container.UserUseCases
import com.acevy.habit_tracker.domain.usecase.habit.DeleteHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.GetHabitsUseCase
import com.acevy.habit_tracker.domain.usecase.habit.InsertHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.UpdateHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habitlog.DeleteHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.habitlog.GetLogsByHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habitlog.InsertHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.habitlog.UpdateHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.habitstack.DeleteHabitStackUseCase
import com.acevy.habit_tracker.domain.usecase.habitstack.GetHabitStacksByUserUseCase
import com.acevy.habit_tracker.domain.usecase.habitstack.InsertHabitStackUseCase
import com.acevy.habit_tracker.domain.usecase.habitstack.UpdateHabitStackUseCase
import com.acevy.habit_tracker.domain.usecase.notificationlog.GetNotificationLogsByUserUseCase
import com.acevy.habit_tracker.domain.usecase.notificationlog.InsertNotificationLogUseCase
import com.acevy.habit_tracker.domain.usecase.reward.GetUserRewardsUseCase
import com.acevy.habit_tracker.domain.usecase.reward.InsertUserRewardUseCase
import com.acevy.habit_tracker.domain.usecase.user.GetUserByIdUseCase
import com.acevy.habit_tracker.domain.usecase.user.InsertUserUseCase
import com.acevy.habit_tracker.domain.usecase.user.UpdateUserUseCase
import com.acevy.habit_tracker.domain.usecase.userlevel.GetUserLevelUseCase
import com.acevy.habit_tracker.domain.usecase.userlevel.InsertUserLevelUseCase
import com.acevy.habit_tracker.domain.usecase.userlevel.UpdateUserLevelUseCase
import com.acevy.habit_tracker.domain.usecase.userlevel.UpdateUserXpAndLevelUseCase
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel

object Injection {

    private lateinit var db: AppDatabase

    fun init(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "habit-db"
        ).fallbackToDestructiveMigration().build()

        initUser()
        initHabit()
        initHabitLog()
        initHabitStack()
        initNotification()
        initUserLevel()
        initUserReward()
    }

    fun provideHabitViewModel(context: Context): HabitViewModel {
        if (!::db.isInitialized) {
            init(context)
        }
        return HabitViewModel(habitUseCases)
    }

    // ---------------- USER ----------------
    lateinit var userUseCases: UserUseCases
        private set

    private fun initUser() {
        val repo = UserRepositoryImpl(db.userDao())
        userUseCases = UserUseCases(
            insertUserUseCase = InsertUserUseCase(repo),
            getUserByIdUseCase = GetUserByIdUseCase(repo),
            updateUserUseCase = UpdateUserUseCase(repo)
        )
    }

    // ---------------- HABIT ----------------
    lateinit var habitUseCases: HabitUseCases
        private set

    private fun initHabit() {
        val repo = HabitRepositoryImpl(db.habitDao())
        habitUseCases = HabitUseCases(
            insertHabitUseCase = InsertHabitUseCase(repo),
            updateHabitUseCase = UpdateHabitUseCase(repo),
            deleteHabitUseCase = DeleteHabitUseCase(repo),
            getHabitsUseCase = GetHabitsUseCase(repo)
        )
    }

    // ---------------- HABIT LOG ----------------
    lateinit var habitLogUseCases: HabitLogUseCases
        private set

    private fun initHabitLog() {
        val repo = HabitLogRepositoryImpl(db.habitLogDao())
        habitLogUseCases = HabitLogUseCases(
            insertHabitLogUseCase = InsertHabitLogUseCase(repo),
            getLogsByHabitUseCase = GetLogsByHabitUseCase(repo),
            updateHabitLogUseCases = UpdateHabitLogUseCase(repo),
            deleteHabitLogUseCase = DeleteHabitLogUseCase(repo)
        )
    }

    // ---------------- HABIT STACK ----------------
    lateinit var habitStackUseCases: HabitStackUseCases
        private set

    private fun initHabitStack() {
        val repo = HabitStackRepositoryImpl(db.habitStackDao())
        habitStackUseCases = HabitStackUseCases(
            insertHabitStackUseCase = InsertHabitStackUseCase(repo),
            deleteHabitStackUseCase = DeleteHabitStackUseCase(repo),
            getHabitStacksByUserUseCase = GetHabitStacksByUserUseCase(repo),
            updateHabitStackUseCases = UpdateHabitStackUseCase(repo)
        )
    }

    // ---------------- NOTIFICATION LOG ----------------
    lateinit var notificationLogUseCases: NotificationLogUseCases
        private set

    private fun initNotification() {
        val repo = NotificationLogRepositoryImpl(db.notificationLogDao())
        notificationLogUseCases = NotificationLogUseCases(
            insertNotificationLogUseCase = InsertNotificationLogUseCase(repo),
            getNotificationLogsByUserUseCase = GetNotificationLogsByUserUseCase(repo)
        )
    }

    // ---------------- USER LEVEL ----------------
    lateinit var userLevelUseCases: UserLevelUseCases
        private set

    private fun initUserLevel() {
        val repo = UserLevelRepositoryImpl(db.userLevelDao())
        userLevelUseCases = UserLevelUseCases(
            getUserLevelByUseCase = GetUserLevelUseCase(repo),
            insertUserLevelUseCase = InsertUserLevelUseCase(repo),
            updateUserLevelUseCase = UpdateUserLevelUseCase(repo),
            updateUserXpAndLevelUseCase = UpdateUserXpAndLevelUseCase(repo)
        )
    }

    // ---------------- USER REWARD ----------------
    lateinit var userRewardUseCases: UserRewardUseCases
        private set

    private fun initUserReward() {
        val repo = UserRewardRepositoryImpl(db.userRewardDao())
        userRewardUseCases = UserRewardUseCases(
            insertUserRewardUseCase = InsertUserRewardUseCase(repo),
            getUserRewardsUseCase = GetUserRewardsUseCase(repo)
        )
    }
}

