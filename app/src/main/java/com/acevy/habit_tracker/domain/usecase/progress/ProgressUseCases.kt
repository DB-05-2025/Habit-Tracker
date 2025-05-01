package com.acevy.habit_tracker.domain.usecase.progress

data class ProgressUseCases(
    val updateUserXpAndLevel: UpdateUserXpAndLevelUseCase,
    val getUserProgress: GetUserProgressUseCase,

    val getHabitsWithTodayStatus: GetHabitsWithTodayStatusUseCase,
    val getCompletedHabits: GetCompletedHabitsUseCase,
    val getMissedHabits: GetMissedHabitsUseCase,
    val getTodayDoneLogs: GetTodayDoneLogsUseCase,
    val getAllLogs: GetAllLogsUseCase
)