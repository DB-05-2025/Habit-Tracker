package com.acevy.habit_tracker.ui.onboard

import androidx.annotation.DrawableRes
import com.acevy.habit_tracker.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {

    data object FirstPage : OnboardingModel(
        image = R.drawable.img_into_1,
        title = "Perubahan Kecil Hasil Besar",
        description = "Impian besar seringkali terwujud melalui serang kaian tindakan kecil yang terarah aplikasi ini hadir untuk menjembatani antara tujuan anda dan kebiasaan sehari-hari anda"
    )

    data object SecondPage : OnboardingModel(
        image = R.drawable.img_into_2,
        title = "Personalisasi Tujuan Anda",
        description = "Apa yang ingin anda capai apakah itu membaca lebih banyak berolahraga teratur menabung atau meningkatkan fokus antomic habits memungkinkan anda menetapkan tujuan"
    )

    data object ThirdPage : OnboardingModel(
        image = R.drawable.img_into_3,
        title = "Bangun Rantai Kebiasaan",
        description = "Setiap hari anda melakukan kebiasaan anda anda memperkuat rantai kemajuan anda fitur statistik visual kami akan membantu anda memantau streak anda dan tetap termotivasi untuk tidak melewatkan satu hari pun"
    )
}