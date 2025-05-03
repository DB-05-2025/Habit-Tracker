package com.acevy.habit_tracker.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.data.remote.response.JournalResponse
import com.acevy.habit_tracker.domain.usecase.habit.HabitUseCases
import com.acevy.habit_tracker.domain.usecase.journal.JournalUseCases
import com.acevy.habit_tracker.utils.ReminderScheduler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JournalViewModel(
    private val useCases: JournalUseCases
) : ViewModel() {

    private val _journals = MutableStateFlow<List<JournalResponse>>(emptyList())
    val journals: StateFlow<List<JournalResponse>> = _journals

    fun getJournals() {
        viewModelScope.launch {
            try {
                _journals.value = useCases.getJournals()
            } catch (e: Exception) {
                Log.d("JournalViewModel", "Error fetching journals: ${e.message}" )
            }
        }
    }

    fun createJournal(journal: JournalResponse) {
        viewModelScope.launch {
            try {
                val created = useCases.createJournal(journal)
                _journals.value += created
            } catch (e: Exception) {
                Log.d("JournalViewModel", "Error creating journal: ${e.message}", )
            }
        }
    }
}
