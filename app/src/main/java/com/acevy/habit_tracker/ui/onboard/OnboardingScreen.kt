package com.acevy.habit_tracker.ui.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val pages = listOf(
        OnboardingModel.FirstPage,
        OnboardingModel.SecondPage,
        OnboardingModel.ThirdPage
    )

    val pagerState = rememberPagerState { pages.size }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            OnboardingGraphUI(onboardingModel = pages[page])
        }

        // Indikator dan Tombol
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Indikator
            IndicatorUI(
                pageSize = pages.size,
                currentPage = pagerState.currentPage,
                selectedColor = Color.Green,
                unselectedColor = Color.Gray
            )

            Spacer(modifier = Modifier.padding(16.dp))

            // Tombol
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tombol Kembali (hanya ditampilkan jika tidak ada di halaman pertama)
                if (pagerState.currentPage > 0) {
                    ButtonUI(
                        text = "Back",
                        backgroundColor = Color.Transparent,
                        textColor = Color.Gray,
                        textStyle = MaterialTheme.typography.bodySmall,
                        fontSize = 13
                    ) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                } else {
                    // Pengatur jarak kosong untuk mempertahankan tata letak saat tombol kembali tidak ditampilkan
                    Spacer(modifier = Modifier.weight(1f))
                }

                // Tombol Lanjut dan Tombol Finish (berubah tergantung halaman)
                val isLastPage = pagerState.currentPage == pages.size - 1
                ButtonUI(
                    text = if (isLastPage) "Mulai" else "Lanjut",
                    backgroundColor = if (isLastPage) Color.Green else Color.Green, // Ubah warna tombol "Lanjut" menjadi hijau
                    textColor = Color.White, // Opsional: Tambahkan warna teks agar lebih kontras
                    textStyle = MaterialTheme.typography.bodySmall,
                    fontSize = 13
                ) {
                    if (isLastPage) {
                        onFinish()
                    } else {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(
        onFinish = {}
    )
}