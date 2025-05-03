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

}