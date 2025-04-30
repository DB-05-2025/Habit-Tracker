package com.acevy.habit_tracker.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import androidx.compose.runtime.*
import com.airbnb.lottie.compose.*

@Composable
fun SplashScreen() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("habit-splash.json")
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.DarkBlue),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(240.dp)
        )
    }
}
