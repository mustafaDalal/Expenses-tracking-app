package com.example.financepal.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financepal.data.db.Transaction

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExpense(transaction: Transaction)

    @Query("DELETE FROM expenses_table WHERE amount = :expense AND timestamp = :timestamp")
    suspend fun deleteExpense(expense : Int, timestamp : Long)

    @Query("DELETE FROM expenses_table")
    suspend fun deleteAllExpenses()

    @Query("SELECT * FROM expenses_table")
    suspend fun getAllExpenses() : List<Transaction>
}