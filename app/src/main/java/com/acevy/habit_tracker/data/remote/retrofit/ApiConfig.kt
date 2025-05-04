package com.acevy.habit_tracker.data.remote.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {


    private const val BASE_URL = "https://68163af532debfe95dbdd3c0.mockapi.io/habit-tracker/"
//    private const val BASE_URL = "https://ofcnpgzapkplcbfngucb.supabase.co"

    fun getApiService(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
//                    .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9mY25wZ3phcGtwbGNiZm5ndWNiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDU1NzIyNjcsImV4cCI6MjA2MTE0ODI2N30.gnbag2viaxYtLwOYvLcWxMxkw_RUW-fbUVEEn5SE70Y")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}