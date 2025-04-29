package com.acevy.habit_tracker.di

import android.content.Context
import androidx.room.Room
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.habit.HabitRepositoryImpl
import com.acevy.habit_tracker.domain.usecase.habit.DeleteHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.GetHabitsUseCase
import com.acevy.habit_tracker.domain.usecase.habit.InsertHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.UpdateHabitUseCase
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel

object Injection {
    fun provideHabitViewModel(context: Context): HabitViewModel {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "habit-db"
        ).build()

        val repo = HabitRepositoryImpl(db.habitDao())

        return HabitViewModel(
            insertHabitUseCase = InsertHabitUseCase(repo),
            getHabitsUseCase = GetHabitsUseCase(repo),
            deleteHabitUseCase = DeleteHabitUseCase(repo),
            updateHabitUseCase = UpdateHabitUseCase(repo)
        )
    }
}