package com.acevy.habit_tracker.domain.usecase.journal

import com.acevy.habit_tracker.data.remote.response.JournalResponse
import com.acevy.habit_tracker.domain.repository.JournalRepository

class UpdateJournalUseCase(private val repo: JournalRepository) {
    suspend operator fun invoke(id: String, journal: JournalResponse): JournalResponse {
        return repo.updateJournal(id, journal)
    }
}