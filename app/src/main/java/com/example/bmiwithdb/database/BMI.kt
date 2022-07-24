package com.example.bmiwithdb.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bmi_history_table")
data class BMI(
    @PrimaryKey(autoGenerate = true)
    var BMIId: Long = 0L,

    @ColumnInfo(name = "date")
    val dateMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "BMI")
    var BMI: Long = 60


)