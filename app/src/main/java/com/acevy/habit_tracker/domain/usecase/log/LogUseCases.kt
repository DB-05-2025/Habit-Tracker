package com.acevy.habit_tracker.domain.usecase.log

data class LogUseCases(
    val generateTodayLogs: GenerateTodayLogsUseCase,
    val getTodayHabitStatus: GetTodayHabitStatusUseCase,
    val updateLog: UpdateHabitLogUseCase,
)

