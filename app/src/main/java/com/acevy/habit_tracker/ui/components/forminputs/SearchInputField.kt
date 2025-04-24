package com.acevy.habit_tracker.ui.components.forminputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun SearchInputField(
    modifier: Modifier = Modifier,
    query: String,
    placeholder: String = "Cari...",
    onQueryChange: (String) -> Unit,
    onClearClick: (() -> Unit)? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .onFocusChanged {
                isFocused = it.isFocused
            },
        shape = RoundedCornerShape(50),
        textStyle = AppType.body14,
        singleLine = true,
        placeholder = {
            Text(text = placeholder, style = AppType.body14)
        },
        trailingIcon = {
            if (isFocused && query.isNotEmpty()) {
                IconButton(onClick = { onClearClick?.invoke() }) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = "Hapus",
                        tint = AppColors.Gray
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Cari",
                    tint = AppColors.Gray
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppColors.GrayLight,
            unfocusedBorderColor = AppColors.GrayLight,
            cursorColor = AppColors.DarkBlue,
            disabledBorderColor = AppColors.GrayLight
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchInputField() {
    var search by remember { mutableStateOf("") }

    HabitTrackerTheme {
        SearchInputField(
            query = search,
            onQueryChange = { search = it },
            onClearClick = { search = "" }
        )
    }
}

