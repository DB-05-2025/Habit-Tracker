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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.acevy.habit_tracker.di.Injection
import com.acevy.habit_tracker.domain.model.habit.Habit
import com.acevy.habit_tracker.ui.model.HabitFormState
import com.acevy.habit_tracker.ui.theme.AppType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateHabitScreen(
    habit: Habit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val viewModel = remember { Injection.provideHabitViewModel(context) }

    val initialForm = HabitFormState(
        title = habit.title,
        note = habit.note.orEmpty(),
        repeatDays = habit.repeatDays?.toSet() ?: emptySet(),
        reminderTime = habit.reminderTime ?: ""
    )

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Edit Habit", style = AppType.bold20) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
        )

        HabitForm(
            navController = navController,
            initialState = initialForm,
            onSubmit = { form ->
                val updatedHabit = habit.copy(
                    title = form.title,
                    note = form.note,
                    repeatDays = form.repeatDays.toList(),
                    reminderTime = form.reminderTime,
                    updatedAt = System.currentTimeMillis()
                )
                viewModel.updateHabit(updatedHabit)
                onBack()
            },
        )
    }
}