package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.domain.model.HabitLog
import com.acevy.habit_tracker.domain.usecase.InsertHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.GetLogsByHabitUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HabitLogViewModel(
    private val addHabitLogUseCase: InsertHabitLogUseCase,
    private val getLogsByHabitUseCase: GetLogsByHabitUseCase,
) : ViewModel() {

    private val _logs = MutableStateFlow<List<HabitLog>>(emptyList())
    val logs: StateFlow<List<HabitLog>> = _logs.asStateFlow()

    fun loadLogs(habitId: Long) {
        viewModelScope.launch {
            getLogsByHabitUseCase(habitId).collect {
                _logs.value = it
            }
        }
    }

    fun addLog(log: HabitLog) {
        viewModelScope.launch {
            addHabitLogUseCase(log)
        }
    }
}