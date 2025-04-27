package com.acevy.habit_tracker.ui.screens.habit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.acevy.habit_tracker.ui.components.buttons.ButtonPrimary
import com.acevy.habit_tracker.ui.components.chips.SelectableChip
import com.acevy.habit_tracker.ui.components.forminputs.SwitchField
import com.acevy.habit_tracker.ui.components.forminputs.TextInputField
import com.acevy.habit_tracker.ui.layout.ScreenContentLayout
import com.acevy.habit_tracker.ui.model.HabitFormState
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun HabitForm(
    initialState: HabitFormState,
    onSubmit: (HabitFormState) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var habitName by rememberSaveable { mutableStateOf(initialState.title) }
    var repeatDays by rememberSaveable { mutableStateOf(initialState.repeatDays) }
    var reminderEnabled by rememberSaveable { mutableStateOf(initialState.reminderEnabled) }
    var note by rememberSaveable { mutableStateOf(initialState.note) }

    ScreenContentLayout(modifier = modifier) {
        TextInputField(
            label = "Nama kebiasaan",
            value = habitName,
            onValueChange = { habitName = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Ulang setiap hari", style = AppType.semiBold14, modifier = Modifier.weight(1f))
            Checkbox(
                checked = repeatDays.size == 7,
                onCheckedChange = { isChecked ->
                    repeatDays = if (isChecked) (0..6).toSet() else emptySet()
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("S", "S", "R", "K", "J", "S", "M").forEachIndexed { i, day ->
                val selected = repeatDays.contains(i)
                SelectableChip(
                    text = day,
                    selected = selected,
                    onClick = {
                        repeatDays = if (selected) repeatDays - i else repeatDays + i
                    }
                )
            }
        }

        Text("*pilih setidaknya satu hari", style = AppType.body10, color = AppColors.Gray)

        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Pengingat", style = AppType.semiBold14, modifier = Modifier.weight(1f))
            SwitchField(
                checked = reminderEnabled,
                onCheckedChange = { reminderEnabled = it }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextInputField(
            label = "Catatan (Opsional)",
            value = note,
            onValueChange = { note = it },
            placeholder = "Opsional"
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonPrimary(
            text = "Tambah",
            onClick = {
                onSubmit(
                    HabitFormState(
                        title = habitName,
                        note = note,
                        repeatDays = repeatDays,
                        reminderEnabled = reminderEnabled
                    )
                )
            },
            fullWidth = true
        )
    }
}
