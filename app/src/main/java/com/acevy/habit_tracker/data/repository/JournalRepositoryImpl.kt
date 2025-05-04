package com.acevy.habit_tracker.data.repository

import com.acevy.habit_tracker.data.remote.response.JournalResponse
import com.acevy.habit_tracker.data.remote.retrofit.ApiService
import com.acevy.habit_tracker.domain.repository.JournalRepository

class JournalRepositoryImpl(private val apiService: ApiService) : JournalRepository {
    override suspend fun getJournals(): List<JournalResponse> {
        return apiService.getJournals()
    }

    override suspend fun createJournal(journal: JournalResponse): JournalResponse {
        return apiService.createJournal(journal)
    }

    override suspend fun updateJournal(id: String, journal: JournalResponse): JournalResponse {
        return apiService.updateJournal(id, journal)
    }

    override suspend fun deleteJournal(id: String) {
        apiService.deleteJournal(id)
    }
}