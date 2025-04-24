package com.acevy.habit_tracker.ui.components.forminputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType
import com.acevy.habit_tracker.ui.theme.HabitTrackerTheme

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    placeholder: String = "",
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val visualTransformation = if (isPassword && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    val trailingIcon: @Composable (() -> Unit)? = if (isPassword) {
        {
            val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(icon, contentDescription = null)
            }
        }
    } else null

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = AppType.semiBold14,
            color = AppColors.GrayDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = AppType.body12,
            shape = AppShapes.Rounded16,
            placeholder = {
                Text(text = placeholder, style = AppType.body12)
            },
            singleLine = true,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
            ),
            trailingIcon = trailingIcon,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AppColors.GreenPrimary,
                unfocusedBorderColor = AppColors.GrayLight,
                cursorColor = AppColors.DarkBlue
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextInputField() {
    HabitTrackerTheme {
        TextInputField(
            value = "Mulyono",
            onValueChange = {},
            label = "Nama depan"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordInputField() {
    HabitTrackerTheme {
        TextInputField(
            value = "mypassword",
            onValueChange = {},
            label = "Password",
            isPassword = true
        )
    }
}