package com.madhav.assignment3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accelerator_table")
class AccelerometerEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var x: Float = 0.0f
    var y: Float = 0.0f
    var z: Float = 0.0f
    var timestamp: Long = 0
}