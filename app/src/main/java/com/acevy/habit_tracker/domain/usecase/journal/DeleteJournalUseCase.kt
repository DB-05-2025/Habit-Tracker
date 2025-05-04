package com.acevy.habit_tracker.domain.usecase.journal

import com.acevy.habit_tracker.domain.repository.JournalRepository

class DeleteJournalUseCase(private val repo: JournalRepository) {
    suspend operator fun invoke(id: String) {
        repo.deleteJournal(id)
    }
}