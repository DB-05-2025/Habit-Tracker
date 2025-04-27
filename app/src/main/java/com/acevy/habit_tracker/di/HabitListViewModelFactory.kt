package com.acevy.habit_tracker.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acevy.habit_tracker.domain.usecase.AddHabitUseCase
import com.acevy.habit_tracker.domain.usecase.GetHabitsUseCase
import com.acevy.habit_tracker.ui.viewmodel.HabitListViewModel

class HabitListViewModelFactory(
    private val getHabitsUseCase: GetHabitsUseCase,
    private val addHabitUseCase: AddHabitUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HabitListViewModel(getHabitsUseCase, addHabitUseCase) as T
    }
}