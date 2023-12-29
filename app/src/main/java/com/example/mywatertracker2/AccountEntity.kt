package com.example.mywatertracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class AccountEntity(
    @ColumnInfo(name = "timeOfDay") val timeOfDay: String,
    @ColumnInfo(name = "amountDrank") val amountDrank: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    )