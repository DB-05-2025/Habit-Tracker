package com.acevy.habit_tracker.ui.screens.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Notifikasi",
            style = AppType.bold20,
            color = AppColors.GrayDark,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(32.dp))

        AsyncImage(
            model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/empty-notif.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL2VtcHR5LW5vdGlmLnBuZyIsImlhdCI6MTc0NTcwNDM0MSwiZXhwIjoxNzc3MjQwMzQxfQ.3Pl0sBfGH5i6urok4NkdmyfrjT11kRf_sOK0CXUb9io",
            contentDescription = "Empty Notification",
            modifier = Modifier
                .height(200.dp)
                .padding(vertical = 8.dp)
        )

        Text(
            text = "Masih belum ada notif nih",
            style = AppType.body14,
            color = AppColors.GrayDark,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(2f))
    }
}
