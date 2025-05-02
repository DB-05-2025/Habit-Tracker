package com.acevy.habit_tracker.data.local.room.db

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "habit_tracker.db"
            )
                .fallbackToDestructiveMigration(false) // ⚠️ WARNING: akan hapus semua data, auto reset skema dan versi, bad practice untuk prod
                .build().also { INSTANCE = it }
        }
    }
}
