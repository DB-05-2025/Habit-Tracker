package com.acevy.habit_tracker.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.acevy.habit_tracker.R

// Font Families
val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
)

val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal)
)

object AppType {

    // Poppins Bold
    val bold24 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, fontFamily = Poppins)
    val bold20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = Poppins)
    val bold16 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = Poppins)
    val bold14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, fontFamily = Poppins)
    val bold10 = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, fontFamily = Poppins)

    // Poppins SemiBold
    val semiBold20 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, fontFamily = Poppins)
    val semiBold14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold, fontFamily = Poppins)
    val semiBold12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold, fontFamily = Poppins)

    // Poppins Medium
    val medium12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium, fontFamily = Poppins)

    // Poppins Regular
    val body14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, fontFamily = Poppins)
    val body12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, fontFamily = Poppins)
    val body10 = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal, fontFamily = Poppins)

    // Roboto Regular
    val roboto14 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal, fontFamily = Roboto)
    val roboto12 = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, fontFamily = Roboto)
    val roboto10 = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal, fontFamily = Roboto)
}