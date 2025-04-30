package com.acevy.habit_tracker.domain.usecase.track

data class TrackUseCases(
    val addLog: AddHabitLogUseCase,
    val getLogsByDate: GetLogsByDateUseCase,
    val deleteLogsByHabit: DeleteLogsByHabitUseCase
)