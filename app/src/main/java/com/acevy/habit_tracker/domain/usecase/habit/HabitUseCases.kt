package com.acevy.habit_tracker.domain.usecase.habit

data class HabitUseCases(
    val addHabit: AddHabitUseCase,
    val getAllHabits: GetAllHabitsUseCase,
    val getHabitById: GetHabitByIdUseCase,
    val updateHabit: UpdateHabitUseCase,
    val deleteHabit: DeleteHabitUseCase
)