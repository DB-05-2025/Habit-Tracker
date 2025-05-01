package com.acevy.habit_tracker.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acevy.habit_tracker.di.Injection

//class ViewModelFactory : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
//        modelClass.isAssignableFrom(HabitViewModel::class.java) -> HabitViewModel(Injection.habitUseCases) as T
//        modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(Injection.userUseCases) as T
//        modelClass.isAssignableFrom(HabitLogViewModel::class.java) -> HabitLogViewModel(AppModule.habitLogUseCases) as T
//        modelClass.isAssignableFrom(NotificationViewModel::class.java) -> NotificationViewModel(AppModule.notificationLogUseCases) as T
//        modelClass.isAssignableFrom(UserLevelViewModel::class.java) -> UserLevelViewModel(AppModule.userLevelUseCases) as T
//        modelClass.isAssignableFrom(HabitStackViewModel::class.java) -> HabitStackViewModel(AppModule.habitStackUseCases) as T
//        else -> throw IllegalArgumentException("Unknown ViewModel: ${modelClass.name}")
//    }
//}