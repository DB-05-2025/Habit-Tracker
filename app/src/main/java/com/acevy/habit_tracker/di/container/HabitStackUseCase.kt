package com.acevy.habit_tracker.di.container

import com.acevy.habit_tracker.domain.usecase.habitstack.DeleteHabitStackUseCase
import com.acevy.habit_tracker.domain.usecase.habitstack.GetHabitStacksByUserUseCase
import com.acevy.habit_tracker.domain.usecase.habitstack.InsertHabitStackUseCase
import com.acevy.habit_tracker.domain.usecase.habitstack.UpdateHabitStackUseCase

data class HabitStackUseCases(
    val insertHabitStackUseCase: InsertHabitStackUseCase,
    val deleteHabitStackUseCase: DeleteHabitStackUseCase,
    val getHabitStacksByUserUseCase: GetHabitStacksByUserUseCase,
    val updateHabitStackUseCases: UpdateHabitStackUseCase
)