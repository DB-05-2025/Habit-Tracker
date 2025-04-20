package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.domain.model.HabitLog
import com.acevy.habit_tracker.domain.usecase.AddHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.AddRewardUseCase
import com.acevy.habit_tracker.domain.usecase.GetLogsByHabitUseCase
import com.acevy.habit_tracker.domain.usecase.UpdateUserLevelUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HabitLogViewModel(
    private val addHabitLogUseCase: AddHabitLogUseCase,
    private val getLogsByHabitUseCase: GetLogsByHabitUseCase,
    private val addRewardUseCase: AddRewardUseCase,
    private val updateUserLevelUseCase: UpdateUserLevelUseCase,
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

            // Jika status "completed" → beri XP & update level
            if (log.status == "completed") {
                val xpAmount = 50 // kamu bisa atur ini
                val userId = 1L    // sementara hardcoded, nanti bisa dinamis

                addRewardUseCase(
                    userId = userId,
                    amount = xpAmount,
                    source = "habit_completed"
                )

                updateUserLevelUseCase(
                    userId = userId,
                    additionalXp = xpAmount
                )
            }
        }
    }
}