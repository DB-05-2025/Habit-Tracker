package com.acevy.habit_tracker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acevy.habit_tracker.domain.usecase.AddHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.AddRewardUseCase
import com.acevy.habit_tracker.domain.usecase.GetLogsByHabitUseCase
import com.acevy.habit_tracker.domain.usecase.UpdateUserLevelUseCase
import com.acevy.habit_tracker.ui.viewmodel.HabitLogViewModel

class HabitLogViewModelFactory(
    private val add: AddHabitLogUseCase,
    private val get: GetLogsByHabitUseCase,
    private val reward: AddRewardUseCase,
    private val levelUp: UpdateUserLevelUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HabitLogViewModel(add, get, reward, levelUp) as T
    }
}