package com.acevy.habit_tracker.domain.usecase.journal

data class JournalUseCases (
    val getJournals: GetJournalsUseCase,
    val createJournal: CreateJournalUseCase,
    val updateJournal: UpdateJournalUseCase,
    val deleteJournal: DeleteJournalUseCase
)