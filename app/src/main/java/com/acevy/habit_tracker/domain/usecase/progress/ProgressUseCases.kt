package com.acevy.habit_tracker.domain.usecase.progress

data class ProgressUseCases(
    val getProgress: GetUserProgressUseCase,
    val updateProgress: UpdateUserProgressUseCase
)