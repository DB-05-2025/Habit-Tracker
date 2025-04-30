package com.acevy.habit_tracker.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

fun generateNotificationMessage(habitTitle: String): AnnotatedString {
    val templates = listOf(
        "Saatnya kamu menyelesaikan kebiasaan %s!",
        "Jangan lupa %s hari ini ya!",
        "Yuk lanjutkan dengan %s sekarang!",
        "Waktunya untuk %s!",
        "Jangan lewatkan %s!"
    )
    val template = templates.random()
    val fullText = template.format(habitTitle)
    val start = fullText.indexOf(habitTitle)
    val end = start + habitTitle.length

    return buildAnnotatedString {
        append(fullText)
        addStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold),
            start = start,
            end = end
        )
    }
}