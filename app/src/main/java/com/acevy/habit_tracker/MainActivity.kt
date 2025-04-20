package com.acevy.habit_tracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.acevy.habit_tracker.data.local.dao.HabitLogDao
import com.acevy.habit_tracker.data.local.database.AppDatabase
import com.acevy.habit_tracker.data.repository.HabitLogRepositoryImpl
import com.acevy.habit_tracker.data.repository.HabitRepositoryImpl
import com.acevy.habit_tracker.di.HabitListViewModelFactory
import com.acevy.habit_tracker.di.HabitLogViewModelFactory
import com.acevy.habit_tracker.domain.usecase.AddHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.AddHabitUseCase
import com.acevy.habit_tracker.domain.usecase.GetHabitsUseCase
import com.acevy.habit_tracker.domain.usecase.GetLogsByHabitUseCase
import com.acevy.habit_tracker.ui.screen.PreviewHabitLogScreen
import com.acevy.habit_tracker.ui.screen.PreviewHabitScreen
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme
import com.acevy.habit_tracker.ui.viewmodel.HabitListViewModel
import com.acevy.habit_tracker.ui.viewmodel.HabitLogViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "habit-db"
        ).build()

        val repo = HabitLogRepositoryImpl(db.habitLogDao())
        val addUseCase = AddHabitLogUseCase(repo)
        val getUseCase = GetLogsByHabitUseCase(repo)

        val viewModelFactory = HabitLogViewModelFactory(addUseCase, getUseCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HabitLogViewModel::class.java]

        setContent {
            HabitTrackerTheme {
                PreviewHabitLogScreen(viewModel)
            }
        }
    }
}
