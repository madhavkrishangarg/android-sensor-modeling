package com.madhav.assignment3

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

interface AccelerometerDao {
    @Insert
    fun insert(accelerometerEntity: AccelerometerEntity)

    @Query("SELECT * FROM accelerator_table")
    fun getAll(): List<AccelerometerEntity>
}