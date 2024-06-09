package com.example.financepal.data.db

interface ExpensesLocalDatabase {

    suspend fun getTotalExpenses() : List<Transaction>?
    suspend fun updateTotalExpenses(transaction: Transaction)

    suspend fun deleteExpense(amount: Int, timeStamp : Long)

    suspend fun deleteAllExpenses()
}