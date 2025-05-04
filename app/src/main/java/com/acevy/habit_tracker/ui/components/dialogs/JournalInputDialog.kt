package com.acevy.habit_tracker.ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import com.acevy.habit_tracker.ui.components.buttons.ButtonSmall
import com.acevy.habit_tracker.ui.components.buttons.ButtonVariant
import com.acevy.habit_tracker.ui.components.forminputs.TextAreaField
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun JournalInputDialog(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit,
    isEditMode: Boolean = false,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Bagaimana harimu?",
                    style = AppType.bold14
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextAreaField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = "Tulis di sini...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ButtonSmall(
                        text = "Batal",
                        onClick = onDismiss,
                        variant = ButtonVariant.Outlined
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    ButtonSmall(
                        text = "Post",
                        onClick = onSubmit,
                        variant = ButtonVariant.Filled
                    )
                }
            }
        }
    }
}
