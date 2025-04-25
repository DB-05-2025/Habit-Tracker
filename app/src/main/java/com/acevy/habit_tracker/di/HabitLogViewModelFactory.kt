package com.acevy.habit_tracker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acevy.habit_tracker.domain.usecase.InsertHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.GetLogsByHabitUseCase
import com.acevy.habit_tracker.ui.viewmodel.HabitLogViewModel

@Suppress("UNCHECKED_CAST")
class HabitLogViewModelFactory(
    private val add: InsertHabitLogUseCase,
    private val get: GetLogsByHabitUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HabitLogViewModel(add, get) as T
    }
}