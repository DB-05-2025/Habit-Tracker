package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.domain.usecase.stack.StackUseCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StackViewModel(
    private val useCases: StackUseCases
) : ViewModel() {

    val allStacks: StateFlow<List<HabitStackEntity>> = useCases
        .getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun createStack(stack: HabitStackEntity) {
        viewModelScope.launch {
            useCases.create(stack)
        }
    }

    fun updateStack(stack: HabitStackEntity) {
        viewModelScope.launch {
            useCases.update(stack)
        }
    }

    fun deleteStack(stack: HabitStackEntity) {
        viewModelScope.launch {
            useCases.delete(stack)
        }
    }

    fun getStackById(id: Int): Flow<HabitStackEntity?> {
        return useCases.getById(id)
    }
}
