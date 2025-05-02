package com.acevy.habit_tracker.ui.screens.habit.habittrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.acevy.habit_tracker.data.local.entity.HabitEntity
import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.components.cards.HabitCardItem
import com.acevy.habit_tracker.ui.components.indicators.AsyncImageWithIndicator
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel

@Composable
fun HabitTrackingPage(
    navController: NavController,
    viewModel: HabitViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val context = LocalContext.current
    val habits by viewModel.habitList.collectAsState()
    var showDialog by remember { mutableStateOf<HabitEntity?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            if (habits.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImageWithIndicator(
                        model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/empty-habit.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL2VtcHR5LWhhYml0LnBuZyIsImlhdCI6MTc0NTgzMDc5MCwiZXhwIjoxNzc3MzY2NzkwfQ.fyk0hSnp_HViUogl20cPgi7dt235fwSNLqMFq9bah1A",
                        contentDescription = "Empty Habit",
                        modifier = Modifier
                            .height(200.dp)
                            .padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Yuk buat habit baru dulu",
                        style = AppType.body14,
                        color = AppColors.GrayDark
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(habits) { habit ->
                        HabitCardItem(
                            habitName = habit.title,
                            showTrailingIcon = false,
                            showDeleteIcon = true,
                            onClick = {
                                navController.navigate(Screen.UpdateHabit.createRoute(habit.id))
                            },
                            onDeleteClick = {
                                showDialog = habit
                            }
                        )
                    }
                }
            }
        }

        if (showDialog != null) {
            AlertDialog(
                onDismissRequest = { showDialog = null },
                title = { Text("Hapus habit") },
                text = { Text("Yakin ingin menghapus habit '${showDialog?.title}'?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteHabit(showDialog!!, context)
                        showDialog = null
                    }) {
                        Text("Hapus")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = null }) {
                        Text("Batal")
                    }
                }
            )
        }

        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddHabit.route)
            },
            shape = CircleShape,
            containerColor = AppColors.GreenPrimary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Tambah Habit",
                tint = Color.White
            )
        }
    }
}
