package com.madhav.assignment3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AccelerometerEntity::class], version = 1)
abstract class AccelerometerDatabase {

    abstract fun accelerometerDao(): AccelerometerDao

    object DatabaseFactory {
        private var INSTANCE: AccelerometerDatabase? = null

        fun getInstance(context: Context): AccelerometerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccelerometerDatabase::class.java,
                    "accelerometer_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}