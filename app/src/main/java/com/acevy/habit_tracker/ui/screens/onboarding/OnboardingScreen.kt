package com.acevy.habit_tracker.ui.screens.onboarding

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.acevy.habit_tracker.data.local.datastore.UserPreferences
import com.acevy.habit_tracker.ui.components.cards.OnboardingCard
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    userPreferences: UserPreferences,
    modifier: Modifier = Modifier
) {
    val isOnboardingCompleted by userPreferences.onboardingCompletedFlow.collectAsState(initial = "")
    Log.d("ONBOARDING DATASTORE", "OnboardingScreen: $isOnboardingCompleted")
    var currentPage by rememberSaveable { mutableIntStateOf(0) }

    val pages = listOf(
        Triple(
            "Perubahan Kecil Hasil Besar",
            "Impian besar seringkali terwujud melalui serangkaian tindakan kecil yang terarah aplikasi ini hadir untuk menjembatani antara tujuan anda dan kebiasaan sehari-hari anda",
            "https://raw.githubusercontent.com/whiteMemoir/dummy-images/refs/heads/main/habit-tracker/undraw_career-progress_vfq5%201.png"
        ),
        Triple(
            "Personalisasi Tujuan Anda",
            "Apa yang ingin anda capai apakah itu membaca lebih banyak berolahraga teratur menabung atau meningkatkan fokus atomic habits memungkinkan anda menetapkan tujuan",
            "https://raw.githubusercontent.com/whiteMemoir/dummy-images/refs/heads/main/habit-tracker/undraw_accept-request_489a%202.png"
        ),
        Triple(
            "Bangun Rantai Kebiasaan",
            "Setiap hari anda melakukan kebiasaan anda anda memperkuat rantai kemajuan anda dan fitur statistik visual kami akan membantu anda memantau streak anda dan tetap termotivasi untuk tidak melewatkan satu hari pun",
            "https://raw.githubusercontent.com/whiteMemoir/dummy-images/refs/heads/main/habit-tracker/undraw_investing_kncz%202.png"
        )
    )

    val (title, desc, imgUrl) = pages[currentPage]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        AsyncImage(
            model = imgUrl,
            contentDescription = title,
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp)
        )

        OnboardingCard(
            title = title,
            description = desc,
            currentPage = currentPage,
            totalPages = pages.size,
            onClickNext = {
                if (currentPage < pages.lastIndex) {
                    currentPage++
                } else {
                    onFinish()
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewOnboardingScreen() {
//    HabitTrackerTheme {
//        OnboardingScreen(
//            onFinish = { /* No-op for preview */ }
//        )
//    }
//}
