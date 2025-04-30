package com.acevy.habit_tracker.ui.components.indicators

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun AsyncImageWithIndicator(
    model: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier
    ) {
        when (painter.state) {
            is coil.compose.AsyncImagePainter.State.Loading,
            is coil.compose.AsyncImagePainter.State.Empty -> {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(24.dp)
                )
            }
            is coil.compose.AsyncImagePainter.State.Error -> {
                // tampilkan fallback atau error icon
                Text("Failed to load image")
            }
            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}
