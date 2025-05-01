package com.acevy.habit_tracker.ui.screens.habit.habitstack

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.acevy.habit_tracker.ui.components.buttons.ButtonPrimary
import com.acevy.habit_tracker.ui.components.cards.HabitCardItem
import com.acevy.habit_tracker.ui.components.forminputs.SearchInputField
import com.acevy.habit_tracker.ui.components.forminputs.TextInputField
import com.acevy.habit_tracker.ui.layout.ScreenContentLayout
import com.acevy.habit_tracker.ui.model.HabitOption
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import kotlinx.coroutines.launch

@Composable
fun StackForm(
    initialStackName: String = "",
    initialHabits: List<HabitOption> = emptyList(),
    onSubmit: (String, List<HabitOption>) -> Unit,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val stackName = remember { mutableStateOf(initialStackName) }
    val searchQuery = remember { mutableStateOf("") }
    val initial = rememberUpdatedState(initialHabits)
    val habits = remember { mutableStateListOf<HabitOption>() }

    LaunchedEffect(initial.value) {
        habits.clear()
        habits.addAll(initial.value.map { it.copy() })
    }

    Log.d("StackForm - INIT HABITS", "StackForm: $initialHabits")
    Log.d("StackForm - HABITS", "StackForm: $habits")


    val filteredIndices = habits.mapIndexedNotNull { index, habit ->
        if (habit.name.contains(searchQuery.value, ignoreCase = true)) index else null
    }

    Log.d("HELLO", "StackForm: $filteredIndices")

    ScreenContentLayout {
        Column(modifier = Modifier.fillMaxSize()) {
            TextInputField(
                value = stackName.value,
                label = "Nama Stack",
                placeholder = "Nama stack diperlukan",
                onValueChange = { stackName.value = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(AppColors.GreenPrimary)
            ) {
                Text(
                    text = "Pilih Habit untuk digabung!",
                    style = AppType.bold16,
                    modifier = Modifier.padding(16.dp),
                    color = AppColors.White
                )

                Column(
                    modifier = Modifier
                        .background(
                            AppColors.White,
                            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    SearchInputField(
                        query = searchQuery.value,
                        onQueryChange = { searchQuery.value = it },
                        placeholder = "Cari Habit"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn {
                        items(filteredIndices) { index ->
                            val habit = habits[index]
                            HabitCardItem(
                                habitName = habit.name,
                                isCheckable = true,
                                isChecked = habit.isSelected,
                                onCheckedChange = {
                                    habits[index] = habit.copy(isSelected = it)
                                },
                                onClick = {
                                    habits[index] = habit.copy(isSelected = !habit.isSelected)
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 72.dp) // biar gak ketiban
            )

            ButtonPrimary(
                text = if (currentRoute == Screen.AddStack.route) "Tambah Stack" else "Update Stack",
                onClick = {
                    val selected = habits.filter { it.isSelected }

                    if (selected.size != 2) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Pilih tepat 2 habit untuk membuat stack")
                        }
                        return@ButtonPrimary
                    }

                    onSubmit(stackName.value, selected)
                },
                fullWidth = true
            )
        }
    }
}
