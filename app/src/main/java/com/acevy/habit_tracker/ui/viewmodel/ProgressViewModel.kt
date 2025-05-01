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
import kotlinx.coroutines.flow.first
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

    val progress: StateFlow<UserProgressEntity?> = useCases
        .getUserProgress()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    init {
        loadAllProgress()
        rewardXpFromAllLogs()
    }

    fun updateXp(xp: Int) {
        viewModelScope.launch {
            useCases.updateUserXpAndLevel(xp)
        }
    }

    private fun loadAllProgress() {
        viewModelScope.launch {
            todayHabits.value = useCases.getHabitsWithTodayStatus()
            completedHabits.value = useCases.getCompletedHabits()
            missedHabits.value = useCases.getMissedHabits()

            // Hitung jumlah habit done hari ini
//            val xpReward = todayHabits.value.count { it.status == HabitStatus.DONE } * 10
//            if (xpReward > 0) {
//                updateXp(xpReward)
//            }
        }
    }


    private fun rewardXpFromLogsToday() {
        viewModelScope.launch {
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val logs = useCases.getTodayDoneLogs(today).first()
            val countDone = logs.count { it.status == HabitStatus.DONE }
            val xp = countDone * 10
            if (xp > 0) {
                updateXp(xp)
            }
        }
    }

    private fun rewardXpFromAllLogs() {
        viewModelScope.launch {
            val allLogs = useCases.getAllLogs()
            val countDone = allLogs.count { it.status == HabitStatus.DONE }
            val xp = countDone * 10
            if (xp > 0) {
                updateXp(xp)
            }
        }
    }
}