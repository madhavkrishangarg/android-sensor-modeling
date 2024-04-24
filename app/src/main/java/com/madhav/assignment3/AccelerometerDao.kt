package com.madhav.assignment3

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccelerometerDao {
    @Insert
    fun upsert(accelerometer: AccelerometerEntity)

    @Query("SELECT * FROM accelerator_table ORDER BY timestamp ASC")
    fun getAccelerometerData(): List<AccelerometerEntity>
}