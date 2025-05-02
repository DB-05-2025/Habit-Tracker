package com.acevy.habit_tracker.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.domain.usecase.habit.HabitUseCases
import com.acevy.habit_tracker.utils.ReminderScheduler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(
    private val useCases: HabitUseCases
) : ViewModel() {

    val habitList: StateFlow<List<HabitEntity>> = useCases
        .getAllHabits()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun getHabitById(id: Int): Flow<HabitEntity?> {
        return useCases.getHabitById(id)
    }

    fun addHabit(habit: HabitEntity, context: Context) {
        viewModelScope.launch {
            useCases.addHabit(habit)
            ReminderScheduler.scheduleTodayReminders(context, listOf(habit))
        }
    }

    fun updateHabit(habit: HabitEntity, context: Context) {
        viewModelScope.launch {
            useCases.updateHabit(habit)
            ReminderScheduler.scheduleTodayReminders(context, listOf(habit))
        }
    }

    fun deleteHabit(habit: HabitEntity, context: Context) {
        viewModelScope.launch {
            useCases.deleteHabit(habit)
            ReminderScheduler.cancelReminderForHabit(context, habit.id)
        }
    }

    suspend fun getAllHabitsOnce(): List<HabitEntity> {
        return useCases.getAllHabits().first()
    }
}
