package com.acevy.habit_tracker.data.local.database

import android.content.Context
import androidx.room.Room

class AppDatabaseBuilder {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "habit-db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}