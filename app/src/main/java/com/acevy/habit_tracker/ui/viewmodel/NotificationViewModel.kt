package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.data.local.entity.NotificationLogEntity
import com.acevy.habit_tracker.domain.usecase.notification.NotificationUseCases
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val useCases: NotificationUseCases
) : ViewModel() {

    val notifications: StateFlow<List<NotificationLogEntity>> = useCases
        .getAllNotifications()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun insertNotification(notification: NotificationLogEntity) {
        viewModelScope.launch {
            useCases.insertNotification(notification)
        }
    }

    fun clearOldNotifications(before: Long) {
        viewModelScope.launch {
            useCases.clearOldNotifications(before)
        }
    }
}
