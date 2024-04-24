package com.madhav.assignment3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AccelerometerEntity::class], version = 2)
abstract class AccelerometerDatabase : RoomDatabase() {

    abstract fun accelerometerDao(): AccelerometerDao

    object DatabaseBuilder {
        @Volatile
        private var INSTANCE: AccelerometerDatabase? = null

        fun getInstance(context: Context): AccelerometerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccelerometerDatabase::class.java,
                    "accelerator_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}