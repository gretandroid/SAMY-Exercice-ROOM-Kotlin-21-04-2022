package com.example.tpandroid_room_kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PersonEntity::class], version = 1)
@TypeConverters(
    DateConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDAO(): PersonDAO

    companion object {
        const val DATABASE_NAME = "AppDatabase.db"

        @Volatile
        private var instance: AppDatabase? = null

        // L'accès à l'instance doit être syncronisé
        private val LOCK = Any()
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(LOCK) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return instance
        }
    }
}