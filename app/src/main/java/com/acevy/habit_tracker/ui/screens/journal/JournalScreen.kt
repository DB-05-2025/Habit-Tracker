package com.acevy.habit_tracker.ui.screens.journal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.acevy.habit_tracker.data.remote.response.JournalResponse
import com.acevy.habit_tracker.ui.ViewModelFactory
import com.acevy.habit_tracker.ui.components.cards.NotificationCard
import com.acevy.habit_tracker.ui.components.forminputs.JournalInputDialog
import com.acevy.habit_tracker.ui.components.indicators.AsyncImageWithIndicator
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.viewmodel.HabitViewModel
import com.acevy.habit_tracker.ui.viewmodel.JournalViewModel
import com.acevy.habit_tracker.ui.viewmodel.NotificationViewModel
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JournalScreen(
    modifier: Modifier = Modifier,
    viewModel: JournalViewModel = viewModel(factory = ViewModelFactory(LocalContext.current))
) {
    val journals by viewModel.journals.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getJournals()
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Jurnal Harian",
                    style = AppType.bold20.copy(textAlign = TextAlign.Start),
                    color = AppColors.GrayDark,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (journals.isEmpty()) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImageWithIndicator(
                            model = "", // Ganti dengan ilustrasi jurnal kosong
                            contentDescription = "Empty Journal",
                            modifier = Modifier
                                .height(200.dp)
                                .padding(vertical = 8.dp)
                        )

                        Text(
                            text = "Belum ada jurnal hari ini",
                            style = AppType.body14,
                            color = AppColors.GrayDark
                        )
                    }
                }
            } else {
                items(journals.reversed()) { journal ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = AppColors.White),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = journal.content ?: "",
                                    style = AppType.body14,
                                    color = AppColors.GrayDark
                                )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = (journal.createdAt ?: "").take(10),
                                style = AppType.body12,
                                color = AppColors.GrayLight
                            )
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Tambah Jurnal")
        }

        if (showDialog) {
            JournalInputDialog(
                value = input,
                onValueChange = { input = it },
                onDismiss = { showDialog = false },
                onSubmit = {
                    viewModel.createJournal(
                        JournalResponse(
                            title = "Jurnal",
                            content = input,
                            createdAt = Instant.now().toString()
                        )
                    )
                    input = ""
                    showDialog = false
                }
            )
        }
    }
}
