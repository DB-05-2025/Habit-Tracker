package com.acevy.habit_tracker.ui.screens.habit.habitstack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.acevy.habit_tracker.data.local.entity.HabitStackEntity
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.components.buttons.ButtonPrimary
import com.acevy.habit_tracker.ui.components.cards.HabitStackCard
import com.acevy.habit_tracker.ui.components.indicators.AsyncImageWithIndicator
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel
import com.acevy.habit_tracker.ui.viewmodel.StackViewModel

@Composable
fun HabitStackingPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: StackViewModel = viewModel(factory = ViewModelFactory(LocalContext.current)),
    habitViewModel: HabitViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val stackList by viewModel.getAllStacks.collectAsState()
    val habitList by habitViewModel.habitList.collectAsState()
    var showDialog by remember { mutableStateOf<HabitStackEntity?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        if (stackList.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImageWithIndicator(
                    model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/empty-stack.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL2VtcHR5LXN0YWNrLnBuZyIsImlhdCI6MTc0NjA4OTMyMywiZXhwIjoxNzc3NjI1MzIzfQ.THDCQfqi66qFW_xOZ2MS6C7kUdmsgbgvisaKP_77rBM",
                    contentDescription = "Empty Stack",
                    modifier = Modifier
                        .height(200.dp)
                        .padding(vertical = 8.dp)
                )

                Text(
                    text = "Belum ada stack dibuat.\nYuk buat yang pertama!",
                    style = AppType.body14,
                    color = AppColors.GrayDark,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(stackList) { stack ->
                    val habitNames = stack.habitIds.mapNotNull { id ->
                        habitList.find { it.id == id }?.title
                    }

                    HabitStackCard(
                        title = stack.title,
                        habits = habitNames,
                        onEditClick = {
                            navController.navigate("updatestack/${stack.id}")
                        },
                        onDeleteClick = {
                            showDialog = stack
                        }
                    )
                }
            }
        }

        if (showDialog != null) {
            AlertDialog(
                onDismissRequest = { showDialog = null },
                title = { Text("Hapus Stack") },
                text = { Text("Yakin ingin menghapus stack '${showDialog?.title}'?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.deleteStack(showDialog!!)
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

        Spacer(modifier = Modifier.height(16.dp))

        ButtonPrimary(
            text = "Tambah Stack",
            onClick = { navController.navigate(Screen.AddStack.route) },
            fullWidth = true
        )
    }
}
