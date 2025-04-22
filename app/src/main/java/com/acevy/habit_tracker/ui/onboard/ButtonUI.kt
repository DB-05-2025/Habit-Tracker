package com.acevy.habit_tracker.ui.onboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonUI(
    text: String = "Next",
    backgroundColor: Color = Color.Green, // Mengubah warna default menjadi hijau
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontSize: Int = 14,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor, // Warna latar tombol
            contentColor = textColor // Warna teks tombol
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NextButton() {
    ButtonUI(text = "Next", backgroundColor = Color.Green) { // Menggunakan warna hijau
        // onClick handler
    }
}

@Preview(showBackground = true)
@Composable
fun BackButton() {
    ButtonUI(
        text = "Back",
        backgroundColor = Color.Transparent, // Tetap transparan untuk tombol "Back"
        textColor = Color.Gray,
        textStyle = MaterialTheme.typography.bodySmall,
        fontSize = 13
    ) {
        // onClick handler
    }
}