package com.acevy.habit_tracker.ui.components.forminputs

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DateInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onDateSelected: (String) -> Unit,
    placeholder: String = "Pilih tanggal"
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val selectedDate = Calendar.getInstance().apply {
                    set(year, month, day)
                }
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                onDateSelected(formatter.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column(modifier = modifier) {
        Text(
            text = label,
            style = AppType.semiBold14,
            color = AppColors.GrayDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            readOnly = true,
            textStyle = AppType.body14,
            shape = AppShapes.Rounded16,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarToday,
                    contentDescription = "Pilih tanggal",
                    tint = AppColors.Gray
                )
            },
            placeholder = {
                Text(text = placeholder, style = AppType.body14)
            },
            visualTransformation = VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.GreenPrimary,
                unfocusedBorderColor = AppColors.GrayLight,
                disabledBorderColor = AppColors.GrayLight,
                cursorColor = AppColors.DarkBlue
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDateInputField() {
    var date by remember { mutableStateOf("") }
    HabitTrackerTheme {
        DateInputField(
            value = date,
            onDateSelected = { date = it },
            label = "Tanggal Lahir"
        )
    }
}

