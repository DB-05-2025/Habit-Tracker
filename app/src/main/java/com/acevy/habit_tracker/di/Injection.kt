package com.acevy.habit_tracker.di

import android.content.Context
import com.acevy.habit_tracker.data.local.room.db.DatabaseBuilder
import com.acevy.habit_tracker.data.repository.HabitRepositoryImpl
import com.acevy.habit_tracker.data.repository.NotificationRepositoryImpl
import com.acevy.habit_tracker.data.repository.ProgressRepositoryImpl
import com.acevy.habit_tracker.data.repository.StackRepositoryImpl
import com.acevy.habit_tracker.data.repository.TrackRepositoryImpl
import com.acevy.habit_tracker.domain.repository.HabitRepository
import com.acevy.habit_tracker.domain.repository.NotificationRepository
import com.acevy.habit_tracker.domain.repository.ProgressRepository
import com.acevy.habit_tracker.domain.repository.StackRepository
import com.acevy.habit_tracker.domain.repository.TrackRepository
import com.acevy.habit_tracker.domain.usecase.habit.AddHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.DeleteHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.GetAllHabitsUseCase
import com.acevy.habit_tracker.domain.usecase.habit.GetHabitByIdUseCase
import com.acevy.habit_tracker.domain.usecase.habit.HabitUseCases
import com.acevy.habit_tracker.domain.usecase.habit.UpdateHabitUseCase
import com.acevy.habit_tracker.domain.usecase.log.GenerateTodayLogsUseCase
import com.acevy.habit_tracker.domain.usecase.log.GetTodayHabitStatusUseCase
import com.acevy.habit_tracker.domain.usecase.log.LogUseCases
import com.acevy.habit_tracker.domain.usecase.log.UpdateHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.notification.ClearOldNotificationsUseCase
import com.acevy.habit_tracker.domain.usecase.notification.DeleteNotificationByHabitIdUseCase
import com.acevy.habit_tracker.domain.usecase.notification.GetAllNotificationsUseCase
import com.acevy.habit_tracker.domain.usecase.notification.InsertNotificationUseCase
import com.acevy.habit_tracker.domain.usecase.notification.NotificationUseCases
import com.acevy.habit_tracker.domain.usecase.progress.GetAllLogsUseCase
import com.acevy.habit_tracker.domain.usecase.progress.GetCompletedHabitsUseCase
import com.acevy.habit_tracker.domain.usecase.progress.GetHabitsWithTodayStatusUseCase
import com.acevy.habit_tracker.domain.usecase.progress.GetMissedHabitsUseCase
import com.acevy.habit_tracker.domain.usecase.progress.GetTodayDoneLogsUseCase
import com.acevy.habit_tracker.domain.usecase.progress.GetUserProgressUseCase
import com.acevy.habit_tracker.domain.usecase.progress.ProgressUseCases
import com.acevy.habit_tracker.domain.usecase.progress.UpdateUserXpAndLevelUseCase
import com.acevy.habit_tracker.domain.usecase.stack.CreateStackUseCase
import com.acevy.habit_tracker.domain.usecase.stack.DeleteStackUseCase
import com.acevy.habit_tracker.domain.usecase.stack.GetAllStacksUseCase
import com.acevy.habit_tracker.domain.usecase.stack.GetStackByIdUseCase
import com.acevy.habit_tracker.domain.usecase.stack.StackUseCases
import com.acevy.habit_tracker.domain.usecase.stack.UpdateStackUseCase
import com.acevy.habit_tracker.domain.usecase.track.AddHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.track.DeleteLogsByHabitUseCase
import com.acevy.habit_tracker.domain.usecase.track.GetLogsByDateUseCase
import com.acevy.habit_tracker.domain.usecase.track.TrackUseCases

object Injection {

    // ======================
    // HABIT MODULE
    // ======================

    fun provideHabitRepository(context: Context): HabitRepository {
        val db = DatabaseBuilder.getInstance(context)
        return HabitRepositoryImpl(db.habitDao())
    }

    fun provideHabitUseCases(context: Context): HabitUseCases {
        val repo = provideHabitRepository(context)
        return HabitUseCases(
            addHabit = AddHabitUseCase(repo),
            getAllHabits = GetAllHabitsUseCase(repo),
            getHabitById = GetHabitByIdUseCase(repo),
            updateHabit = UpdateHabitUseCase(repo),
            deleteHabit = DeleteHabitUseCase(repo)
        )
    }

    // ======================
    // TRACK MODULE
    // ======================
    fun provideTrackRepository(context: Context): TrackRepository {
        val db = DatabaseBuilder.getInstance(context)
        return TrackRepositoryImpl(db.habitLogDao())
    }

    fun provideTrackUseCases(context: Context): TrackUseCases {
        val repo = provideTrackRepository(context)
        return TrackUseCases(
            addLog = AddHabitLogUseCase(repo),
            getLogsByDate = GetLogsByDateUseCase(repo),
            deleteLogsByHabit = DeleteLogsByHabitUseCase(repo)
        )
    }

    // ======================
    // PROGRESS MODULE
    // ======================
    fun provideProgressRepository(context: Context): ProgressRepository {
        val db = DatabaseBuilder.getInstance(context)
        return ProgressRepositoryImpl(db.userProgressDao())
    }

    fun provideProgressUseCases(context: Context): ProgressUseCases {
        val progressRepo = provideProgressRepository(context)
        val habitRepo = provideHabitRepository(context)
        val trackRepo = provideTrackRepository(context)

        return ProgressUseCases(
            updateUserXpAndLevel = UpdateUserXpAndLevelUseCase(progressRepo),
            getUserProgress = GetUserProgressUseCase(progressRepo),

            getHabitsWithTodayStatus = GetHabitsWithTodayStatusUseCase(habitRepo, trackRepo),
            getCompletedHabits = GetCompletedHabitsUseCase(habitRepo, trackRepo),
            getMissedHabits = GetMissedHabitsUseCase(habitRepo, trackRepo),
            getTodayDoneLogs = GetTodayDoneLogsUseCase(trackRepo),
            getAllLogs = GetAllLogsUseCase(trackRepo)
        )
    }


    // ======================
    // STACK MODULE
    // ======================
    fun provideStackRepository(context: Context): StackRepository {
        val db = DatabaseBuilder.getInstance(context)
        return StackRepositoryImpl(db.habitStackDao())
    }

    fun provideStackUseCases(context: Context): StackUseCases {
        val repo = provideStackRepository(context)
        return StackUseCases(
            create = CreateStackUseCase(repo),
            update = UpdateStackUseCase(repo),
            delete = DeleteStackUseCase(repo),
            getAll = GetAllStacksUseCase(repo),
            getById = GetStackByIdUseCase(repo)
        )
    }


    // ======================
    // NOTIFICATION MODULE
    // ======================
    fun provideNotificationRepository(context: Context): NotificationRepository {
        val db = DatabaseBuilder.getInstance(context)
        return NotificationRepositoryImpl(db.notificationLogDao())
    }

    fun provideNotificationUseCases(context: Context): NotificationUseCases {
        val repo = provideNotificationRepository(context)
        return NotificationUseCases(
            insertNotification = InsertNotificationUseCase(repo),
            getAllNotifications = GetAllNotificationsUseCase(repo),
            clearOldNotifications = ClearOldNotificationsUseCase(repo),
            deleteByHabitId = DeleteNotificationByHabitIdUseCase(repo)
        )
    }

    // ======================
    // HABIT LOG MODULE
    // ======================
    fun provideLogUseCases(context: Context): LogUseCases {
        val db = DatabaseBuilder.getInstance(context)
        return LogUseCases(
            generateTodayLogs = GenerateTodayLogsUseCase(
                habitDao = db.habitDao(),
                logDao = db.habitLogDao()
            ),
            getTodayHabitStatus = GetTodayHabitStatusUseCase(
                habitDao = db.habitDao(),
                logDao = db.habitLogDao()
            ),
            updateLog = UpdateHabitLogUseCase(TrackRepositoryImpl(db.habitLogDao())) // ✅ ini
        )
    }
}

