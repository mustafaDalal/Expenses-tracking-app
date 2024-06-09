package com.example.financepal.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val amount : Int,
    val timestamp : Long
)