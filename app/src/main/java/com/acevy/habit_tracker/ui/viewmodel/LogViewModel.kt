package com.acevy.habit_tracker.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.data.local.entity.HabitStatus
import com.acevy.habit_tracker.domain.usecase.log.GenerateTodayLogsUseCase
import com.acevy.habit_tracker.domain.usecase.log.HabitWithStatus
import com.acevy.habit_tracker.domain.usecase.log.LogUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LogViewModel(
    private val useCases: LogUseCases
) : ViewModel() {

    private val _todayHabitStatus = MutableStateFlow<List<HabitWithStatus>>(emptyList())
    val todayHabitStatus: StateFlow<List<HabitWithStatus>> = _todayHabitStatus

    suspend fun generateTodayLogs() {
        useCases.generateTodayLogs()
    }

    fun getTodayHabitStatus(date: String) {
        viewModelScope.launch {
            val data = useCases.getTodayHabitStatus(date)
            _todayHabitStatus.value = data
        }
    }

    fun fetchAndGenerate(date: String) {
        viewModelScope.launch {
            useCases.generateTodayLogs()
            val data = useCases.getTodayHabitStatus(date)
            _todayHabitStatus.value = data
            Log.d("FETCH_LOGS", "Final logs: $data") // pindah ke sini
        }
    }

    fun updateLogStatus(logId: Int, habitId: Int, status: HabitStatus, date: String) {
        viewModelScope.launch {
            val updated = HabitLogEntity(
                id = logId,
                habitId = habitId,
                date = date,
                status = status,
                createdAt = System.currentTimeMillis()
            )
            useCases.updateLog(updated)
            getTodayHabitStatus(date)
        }
    }


}

