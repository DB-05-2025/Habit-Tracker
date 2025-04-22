package com.acevy.habit_tracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.acevy.habit_tracker.ui.onboard.OnboardingScreen
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            HabitTrackerTheme {

                ShowOnboardingScreen()
            }
        }
    }
}

@Composable
private fun ShowOnboardingScreen() {
    val context = LocalContext.current

    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        OnboardingScreen {
            Toast.makeText(context, "Onboarding Completed", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}
