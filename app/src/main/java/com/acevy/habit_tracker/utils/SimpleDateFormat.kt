package com.acevy.habit_tracker.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getFormattedToday(): String {
    val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id"))
    return sdf.format(Date())
}