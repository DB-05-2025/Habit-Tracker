package com.acevy.habit_tracker.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acevy.habit_tracker.di.Injection
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel
import com.acevy.habit_tracker.ui.viewmodel.LogViewModel
import com.acevy.habit_tracker.ui.viewmodel.NotificationViewModel
import com.acevy.habit_tracker.ui.viewmodel.ProgressViewModel
import com.acevy.habit_tracker.ui.viewmodel.StackViewModel
import com.acevy.habit_tracker.ui.viewmodel.TrackViewModel

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(HabitViewModel::class.java) -> {
                val useCases = Injection.provideHabitUseCases(context)
                HabitViewModel(useCases) as T
            }

            modelClass.isAssignableFrom(TrackViewModel::class.java) -> {
                val useCases = Injection.provideTrackUseCases(context)
                TrackViewModel(useCases) as T
            }

            modelClass.isAssignableFrom(ProgressViewModel::class.java) -> {
                val useCases = Injection.provideProgressUseCases(context)
                ProgressViewModel(useCases) as T
            }

            modelClass.isAssignableFrom(StackViewModel::class.java) -> {
                val useCases = Injection.provideStackUseCases(context)
                StackViewModel(useCases) as T
            }

            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> {
                val useCases = Injection.provideNotificationUseCases(context)
                NotificationViewModel(useCases) as T
            }
            modelClass.isAssignableFrom(LogViewModel::class.java) -> {
                val useCases = Injection.provideLogUseCases(context)
                LogViewModel(useCases) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
