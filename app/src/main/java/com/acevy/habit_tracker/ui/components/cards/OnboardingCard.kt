package com.acevy.habit_tracker.ui.components.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.components.buttons.ButtonPrimary
import com.acevy.habit_tracker.ui.components.indicators.DotsIndicator
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme


@Composable
fun OnboardingCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    currentPage: Int,
    totalPages: Int,
    onClickNext: () -> Unit
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = AppShapes.Rounded16,
                ambientColor = Color(0x40000000),
                spotColor = Color(0x40000000)
            )
            .border(
                width = 1.dp,
                color = AppColors.GrayLight,
                shape = AppShapes.Rounded16
            ),
        colors = CardDefaults.cardColors(containerColor = AppColors.White),
        shape = AppShapes.Rounded16
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DotsIndicator(currentPage = currentPage, totalPages = totalPages)

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = title,
                style = AppType.bold20,
                color = AppColors.GrayDark,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = AppType.roboto14,
                color = AppColors.GrayDark,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonPrimary(
                text = "Lanjut",
                onClick = onClickNext,
                fullWidth = true
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewSecondOnboardingCard() {
    HabitTrackerTheme {
        OnboardingCard(
            title = "Bangun Rantai Kebiasaan",
            description = "Apa yang ingin anda capai apakah itu membaca lebih banyak berolahraga teratur menabung atau meningkatkan fokus atomic habits memungkinkan anda menetapkan tujuan",
            currentPage = 2,
            totalPages = 3,
            onClickNext = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstOnboardingCard() {
    HabitTrackerTheme {
        OnboardingCard(
            title = "Bangun Rantai Kebiasaan",
            description = " Setiap hari anda melakukan kebiasaan anda \n" +
                    "anda memperkuat rantai kemajuan anda fitur \n" +
                    "  statistik visual kami akan membantu anda \n" +
                    "memantau streak anda dan tetap termotivasi \n" +
                    "     untuk tidak melewatkan satu hari pun",
            currentPage = 1,
            totalPages = 3,
            onClickNext = {}
        )
    }
}