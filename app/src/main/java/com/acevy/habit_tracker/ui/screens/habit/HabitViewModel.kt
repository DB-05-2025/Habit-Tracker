package com.acevy.habit_tracker.ui.screens.habit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.domain.model.habit.Habit
import com.acevy.habit_tracker.domain.usecase.habit.DeleteHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.GetHabitsUseCase
import com.acevy.habit_tracker.domain.usecase.habit.InsertHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.UpdateHabitUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HabitViewModel(
    private val insertHabitUseCase: InsertHabitUseCase,
    private val getHabitsUseCase: GetHabitsUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase
) : ViewModel() {

    private val _habitName = mutableStateOf("")
    val habitName: State<String> get() = _habitName

    fun onHabitNameChange(name: String) {
        _habitName.value = name
    }

    fun insertHabit(habit: Habit) {
        viewModelScope.launch {
            insertHabitUseCase(habit)
        }
    }

    fun getHabits(userId: Long): Flow<List<Habit>> {
        return getHabitsUseCase(userId)
    }
}