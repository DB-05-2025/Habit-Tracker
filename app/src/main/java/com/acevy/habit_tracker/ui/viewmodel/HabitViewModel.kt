package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.di.container.HabitUseCases
import com.acevy.habit_tracker.domain.model.habit.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HabitViewModel(private val useCases: HabitUseCases) : ViewModel() {
    val habits = MutableStateFlow<List<Habit>>(emptyList())

    fun load(userId: Long) {
        viewModelScope.launch {
            useCases.getHabitsUseCase(userId).collect {
                habits.value = it
            }
        }
    }

    fun insertHabit(habit: Habit) {
        viewModelScope.launch {
            useCases.insertHabitUseCase(habit)
            load(habit.userId)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            useCases.updateHabitUseCase(habit)
            load(habit.userId)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            useCases.deleteHabitUseCase(habit)
            load(habit.userId)
        }
    }
}