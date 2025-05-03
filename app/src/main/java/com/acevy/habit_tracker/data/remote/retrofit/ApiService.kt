package com.acevy.habit_tracker.data.remote.retrofit

import com.acevy.habit_tracker.data.remote.response.JournalResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("journals")
    suspend fun getJournals(): List<JournalResponse>

    @POST("journals")
    suspend fun createJournal(journal: JournalResponse): JournalResponse

}