package com.acevy.habit_tracker.data.remote.retrofit

import com.acevy.habit_tracker.data.remote.response.JournalResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("journals")
    suspend fun getJournals(): List<JournalResponse>

    @POST("journals")
    suspend fun createJournal(@Body journal: JournalResponse): JournalResponse

    @PUT("journals/{id}")
    suspend fun updateJournal(
        @Path("id") id: String,
        @Body journal: JournalResponse
    ): JournalResponse

    @DELETE("journals/{id}")
    suspend fun deleteJournal(
        @Path("id") id: String
    ): Unit


}