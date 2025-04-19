package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.domain.model.Habit
import com.acevy.habit_tracker.domain.usecase.AddHabitUseCase
import com.acevy.habit_tracker.domain.usecase.GetHabitsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HabitListViewModel(
    private val getHabitsUseCase: GetHabitsUseCase,
    private val addHabitUseCase: AddHabitUseCase,
) : ViewModel() {

    private val _habitList = MutableStateFlow<List<Habit>>(emptyList())
    val habitList: StateFlow<List<Habit>> = _habitList.asStateFlow()

    fun loadHabits(userId: Long) {
        viewModelScope.launch {
            getHabitsUseCase(userId).collect {
                _habitList.value = it
            }
        }
    }

    fun addHabit(habit: Habit) {
        viewModelScope.launch {
            addHabitUseCase(habit)
        }
    }
}