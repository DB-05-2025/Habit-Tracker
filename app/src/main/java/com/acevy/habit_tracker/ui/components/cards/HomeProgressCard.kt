package com.acevy.habit_tracker.ui.components.cards

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun HomeProgressCard(
    text: String,
    percent: Float,
    modifier: Modifier = Modifier
) {
    // Animasi dari 0 ke percent
    val animatedProgress by animateFloatAsState(
        targetValue = percent,
        animationSpec = tween(durationMillis = 800),
        label = "progressAnim"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(AppShapes.Rounded16)
            .background(
                Brush.linearGradient(
                    listOf(AppColors.GreenPrimary, AppColors.GreenAccent)
                )
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .size(86.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier.fillMaxSize(),
                    color = AppColors.Orange,
                    strokeWidth = 14.dp,
                    trackColor = AppColors.White,
                    gapSize = 0.dp,
                    strokeCap = StrokeCap.Butt
                )

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(AppColors.GreenPrimary, shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${(animatedProgress * 100).toInt()}%",
                        style = AppType.bold20,
                        color = AppColors.White
                    )
                }
            }

            Spacer(modifier = Modifier.width(24.dp)) // 👉 kasih sedikit lebih banyak spasi

            // TEKS DALAM COLUMN
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    style = AppType.bold16,
                    color = AppColors.White
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewHomeProgressCard() {
    HabitTrackerTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.White)
                .padding(24.dp)
        ) {
            HomeProgressCard(
                percent = 0f,
                text = "3 dari 5 kebiasaan\nhari ini kamu telah\ndiselesaikan"
            )
        }
    }
}
