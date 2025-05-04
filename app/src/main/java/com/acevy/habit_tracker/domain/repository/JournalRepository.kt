package com.acevy.habit_tracker.domain.repository

import com.acevy.habit_tracker.data.remote.response.JournalResponse

interface JournalRepository {
    suspend fun getJournals(): List<JournalResponse>
    suspend fun createJournal(journal: JournalResponse): JournalResponse
    suspend fun updateJournal(id: String, journal: JournalResponse): JournalResponse
    suspend fun deleteJournal(id: String)
}