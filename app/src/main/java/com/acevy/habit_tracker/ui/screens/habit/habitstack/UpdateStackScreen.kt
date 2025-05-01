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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.model.HabitOption
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.viewmodel.StackViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStackScreen(
    stack: HabitStackEntity,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: StackViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    // TODO: Replace dummy with ViewModel data
    val dummyHabits = listOf(
        1 to "Bekerja",
        2 to "Olahraga",
        3 to "Makan",
        4 to "Tidur",
        5 to "Mandi"
    ).map { (id, name) ->
        HabitOption(
            id = id,
            name = name,
            isSelected = id in stack.habitIds
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Update Stack", style = AppType.bold20) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        StackForm(
            initialStackName = stack.title,
            initialHabits = dummyHabits,
            onSubmit = { name, selectedHabits ->
                val updatedStack = stack.copy(
                    title = name,
                    habitIds = selectedHabits.filter { it.isSelected }.map { it.id }
                )
                viewModel.updateStack(updatedStack)
                onBack()
            },
            navController = navController
        )
    }
}
