package com.example.financepal.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financepal.data.dao.ExpensesDao

@Database(entities = [Transaction::class], exportSchema = false, version = 1)
abstract class ExpensesDatabase : RoomDatabase() {

    abstract fun expensesDao() : ExpensesDao
}