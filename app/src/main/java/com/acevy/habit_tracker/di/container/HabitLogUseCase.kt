package com.acevy.habit_tracker.di.container

import com.acevy.habit_tracker.domain.usecase.habitlog.DeleteHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.habitlog.GetLogsByHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habitlog.InsertHabitLogUseCase
import com.acevy.habit_tracker.domain.usecase.habitlog.UpdateHabitLogUseCase

data class HabitLogUseCases(
    val insertHabitLogUseCase: InsertHabitLogUseCase,
    val getLogsByHabitUseCase: GetLogsByHabitUseCase,
    val updateHabitLogUseCases: UpdateHabitLogUseCase,
    val deleteHabitLogUseCase: DeleteHabitLogUseCase,
)