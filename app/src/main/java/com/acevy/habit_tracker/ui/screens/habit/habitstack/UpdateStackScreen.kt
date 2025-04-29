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
import androidx.navigation.NavHostController
import com.acevy.habit_tracker.domain.model.habitstack.HabitStack
import com.acevy.habit_tracker.ui.model.HabitOption
import com.acevy.habit_tracker.ui.theme.AppType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStackScreen(
    stack: HabitStack,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val dummyHabits = listOf(
        1L to "Bekerja",
        2L to "Olahraga",
        3L to "Makan",
        4L to "Tidur",
        5L to "Mandi"
    ).map { (id, name) ->
        HabitOption(
            id = id,
            name = name,
            isSelected = id == stack.stackedHabitId
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
            title = "Update Stack",
            initialStackName = "",
            initialHabits = dummyHabits,
            onBack = onBack,
            onSubmit = { name, selectedHabits ->
                Log.d("STACK", "Updated: $name with ${selectedHabits.count { it.isSelected }} habits")
                onBack()
            },
            navController = navController
        )
    }
}
