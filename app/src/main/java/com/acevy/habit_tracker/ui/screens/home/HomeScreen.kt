package com.acevy.habit_tracker.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.acevy.habit_tracker.data.local.datastore.UserPreferences
import com.acevy.habit_tracker.ui.components.cards.ProgressCard
import com.acevy.habit_tracker.ui.components.navigation.BottomNavBar
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.utils.getFormattedToday
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    userPreferences: UserPreferences,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val userName by userPreferences.userNameFlow.collectAsState(initial = "")
    val today = remember { getFormattedToday() }

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        containerColor = AppColors.White
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = today,
                style = AppType.body12,
                color = AppColors.GrayDark
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                buildAnnotatedString {
                    append("Hello, ")
                    withStyle(SpanStyle(color = AppColors.GreenPrimary)) {
                        append(userName)
                    }
                    append("!")
                },
                style = AppType.bold20
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProgressCard(percent = 0f)

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Kebiasaan Hari ini",
                style = AppType.bold16,
                color = AppColors.GrayDark
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Empty Habit Placeholder
            AsyncImage(
                model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/empty-habit.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL2VtcHR5LWhhYml0LnBuZyIsImlhdCI6MTc0NTY5OTM5NiwiZXhwIjoxNzc3MjM1Mzk2fQ.e7Gbfc-MgnQk6fZ6kHxyySGAyHdVqGDzNv7yOcxUD6w",
                contentDescription = "Empty Habit",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(vertical = 16.dp)
            )

            Text(
                text = "Yuk buat habit baru dulu",
                style = AppType.body14,
                color = AppColors.GrayDark,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
