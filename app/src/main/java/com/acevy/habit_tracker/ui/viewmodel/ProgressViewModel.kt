package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.UserProgressEntity
import com.acevy.habit_tracker.domain.usecase.progress.ProgressUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProgressViewModel(
    private val useCases: ProgressUseCases
) : ViewModel() {

    val progress: StateFlow<UserProgressEntity?> = useCases
        .getProgress()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun updateProgress(data: UserProgressEntity) {
        viewModelScope.launch {
            useCases.updateProgress(data)
        }
    }
}
