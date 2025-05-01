package com.acevy.habit_tracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acevy.habit_tracker.di.container.UserUseCases
import com.acevy.habit_tracker.domain.model.user.User
import kotlinx.coroutines.launch

class UserViewModel(private val useCases: UserUseCases) : ViewModel() {
    fun insertUser(user: User) {
        viewModelScope.launch {
            useCases.insertUserUseCase(user)
        }
    }
}