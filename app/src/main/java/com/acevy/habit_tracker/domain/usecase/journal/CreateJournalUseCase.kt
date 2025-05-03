package com.acevy.habit_tracker.domain.usecase.journal

import com.acevy.habit_tracker.data.remote.response.JournalResponse
import com.acevy.habit_tracker.domain.repository.JournalRepository

class CreateJournalUseCase(private val repository: JournalRepository) {
    suspend operator fun invoke(journal: JournalResponse): JournalResponse {
        return repository.createJournal(journal)
    }
}