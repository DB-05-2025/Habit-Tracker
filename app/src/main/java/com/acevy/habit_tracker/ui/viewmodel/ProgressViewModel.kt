package com.acevy.habit_tracker.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.HabitStatus
import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import com.acevy.habit_tracker.domain.usecase.progress.ProgressUseCases
import com.acevy.habit_tracker.domain.model.HabitWithStatus
import com.acevy.habit_tracker.domain.model.HabitWithProgress
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProgressViewModel(
    private val useCases: ProgressUseCases
) : ViewModel() {

    val todayHabits = mutableStateOf<List<HabitWithStatus>>(emptyList())
    val completedHabits = mutableStateOf<List<HabitWithProgress>>(emptyList())
    val missedHabits = mutableStateOf<List<HabitWithProgress>>(emptyList())
    val xpToday = mutableStateOf(0)


    val progress: StateFlow<UserProgressEntity?> = useCases
        .getUserProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        rewardXpFromPastLogs()
        observeTodayXp()
        loadAllProgress()
    }

    fun updateXp(xp: Int) {
        viewModelScope.launch {
            useCases.updateUserXpAndLevel(xp)
        }
    }

    private fun updateXpTodayOnly(xp: Int) {
        xpToday.value = xp
    }

    private fun loadAllProgress() {
        viewModelScope.launch {
            todayHabits.value = useCases.getHabitsWithTodayStatus()
            completedHabits.value = useCases.getCompletedHabits()
            missedHabits.value = useCases.getMissedHabits()

        }
    }

    private fun rewardXpFromPastLogs() {
        viewModelScope.launch {
            val allLogs = useCases.getAllLogs()
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val xp = allLogs
                .filter { it.date != today && it.status == HabitStatus.DONE }
                .size * 10

            if (xp > 0) updateXp(xp)
        }
    }

    private fun observeTodayXp() {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        viewModelScope.launch {
            useCases.getTodayDoneLogs(today).collect { logs ->
                val xp = logs.count { it.status == HabitStatus.DONE } * 10
                updateXpTodayOnly(xp)
            }
        }
    }
}