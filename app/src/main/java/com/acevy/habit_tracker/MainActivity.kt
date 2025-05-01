package com.acevy.habit_tracker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.acevy.habit_tracker.di.Injection
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Injection.init(applicationContext)

        setContent {
            HabitTrackerTheme {
                HabitTrackerApp()
            }
        }
    }
}