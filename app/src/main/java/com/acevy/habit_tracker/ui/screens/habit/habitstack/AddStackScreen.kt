package com.acevy.habit_tracker.ui.screens.habit.habitstack

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.model.HabitOption
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel
import com.acevy.habit_tracker.ui.viewmodel.StackViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStackScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    habitViewModel: HabitViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    stackViewModel: StackViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)) // ✅ tambahkan ini
) {
    val habits by habitViewModel.habitList.collectAsState()
    val habitOptions = remember(habits) {
        habits.map {
            HabitOption(
                id = it.id,
                name = it.title,
                isSelected = false
            )
        }.toMutableStateList()
    }

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Buat Stack", style = AppType.bold20) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        StackForm(
            initialStackName = "",
            initialHabits = habitOptions,
            onSubmit = { name, selectedHabits ->
                val newStack = HabitStackEntity(
                    title = name,
                    habitIds = selectedHabits.map { it.id },
                    createdAt = System.currentTimeMillis()
                )
                stackViewModel.insertStack(newStack)
                onBack()
            },
            navController = navController
        )
    }
}
