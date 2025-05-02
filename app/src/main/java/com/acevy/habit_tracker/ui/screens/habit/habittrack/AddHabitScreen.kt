package com.acevy.habit_tracker.ui.screens.habit.habittrack

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.di.Injection
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.model.HabitFormState
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HabitViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)) // ✅ DI PARAM
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Buat Habit", style = AppType.bold20) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
        )

        HabitForm(
            navController = navController,
            initialState = HabitFormState(
                title = "",
                repeatDays = emptySet(),
                reminderTime = "",
                note = null
            ),
            onSubmit = { form ->
                val newHabit = HabitEntity(
                    id = 0,
                    title = form.title,
                    note = form.note,
                    repeatDays = form.repeatDays.toList(),
                    reminderTime = if (form.reminderTime.isNotEmpty()) form.reminderTime else null,
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis()
                )
                Log.d("CHECK", "AddHabitScreen: $newHabit")
                viewModel.addHabit(newHabit, context)
                onBack()
            },
        )
    }
}
