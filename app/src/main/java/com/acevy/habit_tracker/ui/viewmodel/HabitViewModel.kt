package com.acevy.habit_tracker.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.di.Injection.habitUseCases
import com.acevy.habit_tracker.di.container.HabitUseCases
import com.acevy.habit_tracker.domain.model.habit.Habit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class HabitViewModel(private val useCases: HabitUseCases) : ViewModel() {
    private val _habits = MutableStateFlow<List<Habit>>(emptyList())
    val habits: StateFlow<List<Habit>> = _habits.asStateFlow()

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

    fun load(userId: Long) {
        viewModelScope.launch {
            habitUseCases.getHabitsUseCase(userId).collect { allHabits ->
                _habits.value = allHabits
            }
        }
    }
}