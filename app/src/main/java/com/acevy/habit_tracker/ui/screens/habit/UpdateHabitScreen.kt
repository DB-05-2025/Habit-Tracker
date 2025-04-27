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
import com.acevy.habit_tracker.domain.model.habit.Habit
import com.acevy.habit_tracker.ui.components.buttons.ButtonPrimary
import com.acevy.habit_tracker.ui.components.chips.SelectableChip
import com.acevy.habit_tracker.ui.components.forminputs.SwitchField
import com.acevy.habit_tracker.ui.components.forminputs.TextInputField
import com.acevy.habit_tracker.ui.layout.ScreenContentLayout
import com.acevy.habit_tracker.ui.model.HabitFormState
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateHabitScreen(
    habit: Habit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val initialForm = HabitFormState(
        title = habit.title,
        note = habit.note.orEmpty(),
        repeatDays = habit.repeatDays?.toSet() ?: emptySet(),
        reminderEnabled = !habit.reminderTime.isNullOrBlank()
    )

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Edit Habit", style = AppType.bold20) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
        )

        HabitForm(
            initialState = initialForm,
            onSubmit = { form ->
                // Buat Habit baru berdasarkan input user
                val updatedHabit = habit.copy(
                    title = form.title,
                    note = form.note,
                    repeatDays = form.repeatDays.toList(),
                    reminderTime = if (form.reminderEnabled) "06:00" else null,
                    updatedAt = System.currentTimeMillis()
                )

                // TODO: simpan pakai viewModel.updateHabit(updatedHabit)
                onBack()
            },
            onBack = onBack
        )
    }
}