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
import com.acevy.habit_tracker.ui.model.HabitOption
import com.acevy.habit_tracker.ui.theme.AppType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStackScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
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
            isSelected = false
        )
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
            initialHabits = dummyHabits,
            onSubmit = { name, selectedHabits ->
                Log.d("STACK", "Submitted: $name with ${selectedHabits.count { it.isSelected }} habits")
                onBack()
            },
            navController = navController
        )
    }
}
