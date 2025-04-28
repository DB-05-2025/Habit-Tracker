package com.acevy.habit_tracker.ui.screens.habit.habittrack

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.acevy.habit_tracker.ui.components.buttons.ButtonPrimary
import com.acevy.habit_tracker.ui.components.chips.SelectableChip
import com.acevy.habit_tracker.ui.components.forminputs.SwitchField
import com.acevy.habit_tracker.ui.components.forminputs.TextInputField
import com.acevy.habit_tracker.ui.layout.ScreenContentLayout
import com.acevy.habit_tracker.ui.model.HabitFormState
import com.acevy.habit_tracker.ui.navigation.Screen
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitForm(
    initialState: HabitFormState,
    onSubmit: (HabitFormState) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    var habitName by rememberSaveable { mutableStateOf(initialState.title) }
    var repeatDays by rememberSaveable { mutableStateOf(initialState.repeatDays) }
    var reminderEnabled by rememberSaveable { mutableStateOf(initialState.reminderTime.isNotEmpty()) }
    var reminderTime by rememberSaveable { mutableStateOf(initialState.reminderTime) }
    var note by rememberSaveable { mutableStateOf(initialState.note) }
    val showTimePicker = remember { mutableStateOf(false) }

    Log.d("DEBUG", "reminderTime raw = '${initialState.reminderTime}'")


    val (initHour, initMinute) = initialState.reminderTime
        .takeIf { it.contains(":") }
        ?.split(":")
        ?.mapNotNull { it.toIntOrNull() }
        ?.let {
            val hour = it.getOrNull(0) ?: 0
            val minute = it.getOrNull(1) ?: 0
            hour to minute
        } ?: (0 to 0)

    Log.d("DEBUG", "reminderTime raw = '${initialState.reminderTime}'")

    val timePickerState = rememberTimePickerState(
        initialHour = initHour,
        initialMinute = initMinute,
        is24Hour = true
    )

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

        Spacer(modifier = Modifier.height(2.dp))

        Text("*pilih setidaknya satu hari", style = AppType.body10, color = AppColors.Gray)

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pengingat", style = AppType.semiBold14, modifier = Modifier.weight(1f))
            SwitchField(
                checked = reminderEnabled,
                onCheckedChange = {
                    reminderEnabled = it
                    showTimePicker.value = it
                    if (!it) reminderTime = ""
                }
            )
        }

        if (reminderTime.isNotEmpty()) {
            Text(
                text = "Jam pengingat: $reminderTime",
                style = AppType.body10,
                color = AppColors.GrayDark,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextInputField(
            label = "Catatan (Opsional)",
            value = note ?: "",
            onValueChange = { note = it },
            placeholder = "Opsional"
        )

        Spacer(modifier = Modifier.weight(1f))
        Log.d("ROUTE", "HabitForm: $currentRoute ${Screen.AddHabit.route}")
        ButtonPrimary(
            text = if (currentRoute == Screen.AddHabit.route) "Tambah" else "Update",
            fullWidth = true,
            onClick = {
                if (repeatDays.isEmpty()) {
                    // tampilkan toast/snackbar atau return
                    return@ButtonPrimary
                }
                if (reminderTime.isEmpty()) {
                    // tampilkan toast/snackbar atau return
                    return@ButtonPrimary
                }

                val result = HabitFormState(
                    title = habitName,
                    repeatDays = repeatDays,
                    reminderTime = reminderTime,
                    note = note,
                )

                onSubmit(result)

                Log.d("RESULT", "title=${result.title}, days=${result.repeatDays}, time=${result.reminderTime}, note=${result.note}")
            },
        )

        if (showTimePicker.value) {
            com.acevy.habit_tracker.ui.components.dialogs.TimePickerDialog(
                onDismiss = { showTimePicker.value = false },
                onConfirm = {
                    val hour = timePickerState.hour
                    val minute = timePickerState.minute
                    reminderTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
                    showTimePicker.value = false
                }
            ) {
                TimePicker(state = timePickerState)
            }
        }
    }
}
