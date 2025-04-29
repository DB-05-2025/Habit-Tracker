package com.acevy.habit_tracker.ui.screens.habit.habittrack

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun HabitTrackingPage(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ✅ Kolom isi utama
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/empty-habit.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL2VtcHR5LWhhYml0LnBuZyIsImlhdCI6MTc0NTgzMDc5MCwiZXhwIjoxNzc3MzY2NzkwfQ.fyk0hSnp_HViUogl20cPgi7dt235fwSNLqMFq9bah1A",
                    contentDescription = "Empty Habit",
                    modifier = Modifier
                        .height(200.dp)
                        .padding(vertical = 8.dp)
                )

                Text(
                    "Yuk buat habit baru dulu",
                    style = AppType.body14,
                    color = AppColors.GrayDark
                )
            }
        }

        // ✅ Ini baris pentingnya. Sekarang `Modifier.align()` bisa dipakai
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddHabit.route)
            },
            shape = CircleShape,
            containerColor = AppColors.GreenPrimary,
            modifier = Modifier
                .align(Alignment.BottomEnd) // ⬅️ valid sekarang karena di BoxScope
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
