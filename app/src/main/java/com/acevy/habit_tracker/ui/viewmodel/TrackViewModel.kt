package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.HabitLogEntity
import com.acevy.habit_tracker.domain.usecase.track.TrackUseCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TrackViewModel(
    private val useCases: TrackUseCases
) : ViewModel() {

    fun addLog(log: HabitLogEntity) {
        viewModelScope.launch {
            useCases.addLog(log)
        }
    }

    fun deleteLogsByHabit(habitId: Int) {
        viewModelScope.launch {
            useCases.deleteLogsByHabit(habitId)
        }
    }

    fun getLogsByDate(date: String): Flow<List<HabitLogEntity>> {
        return useCases.getLogsByDate(date)
    }
}
