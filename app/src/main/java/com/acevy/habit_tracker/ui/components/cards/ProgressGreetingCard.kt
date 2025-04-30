package com.acevy.habit_tracker.ui.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.acevy.habit_tracker.ui.components.indicators.AsyncImageWithIndicator
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun ProgressGreetingCard(
    userName: String?,
    modifier: Modifier = Modifier
) {
    Card(
        shape = AppShapes.Rounded16,
        colors = CardDefaults.cardColors(containerColor = AppColors.GreenPrimary),
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = "$userName, selalu cek status progresmu ya! 🔥",
                    style = AppType.semiBold14,
                    color = AppColors.White,
                )
            }

            AsyncImageWithIndicator(
                model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/piala-habit.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL3BpYWxhLWhhYml0LnBuZyIsImlhdCI6MTc0NTcyOTEyOSwiZXhwIjoxNzc3MjY1MTI5fQ.puJI0Lm_rJcdL1XcSIU6h_pRDwazGI-17MLd_XrEfTI",
                contentDescription = "Trophy",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProgressScreen() {
    HabitTrackerTheme {
        ProgressGreetingCard(userName = "Mulyono", modifier = Modifier.padding(16.dp))
    }
}