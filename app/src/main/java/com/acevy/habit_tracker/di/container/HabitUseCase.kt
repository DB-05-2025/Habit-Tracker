package com.acevy.habit_tracker.di.container

import com.acevy.habit_tracker.domain.usecase.habit.DeleteHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.GetHabitsUseCase
import com.acevy.habit_tracker.domain.usecase.habit.InsertHabitUseCase
import com.acevy.habit_tracker.domain.usecase.habit.UpdateHabitUseCase

data class HabitUseCases(
    val insertHabitUseCase: InsertHabitUseCase,
    val updateHabitUseCase: UpdateHabitUseCase,
    val deleteHabitUseCase: DeleteHabitUseCase,
    val getHabitsUseCase: GetHabitsUseCase,
)