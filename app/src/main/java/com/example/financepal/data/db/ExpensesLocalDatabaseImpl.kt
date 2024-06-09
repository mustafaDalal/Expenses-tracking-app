package com.example.financepal.data.db

import com.example.financepal.data.dao.ExpensesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesLocalDatabaseImpl(private val dao : ExpensesDao) : ExpensesLocalDatabase {
    override suspend fun getTotalExpenses(): List<Transaction>? {
        return dao.getAllExpenses()
    }

    override suspend fun updateTotalExpenses(transaction: Transaction) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.addExpense(transaction)
        }
    }

    override suspend fun deleteExpense(amount: Int, timeStamp: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteExpense(amount, timeStamp)
        }
    }

    override suspend fun deleteAllExpenses() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAllExpenses()
        }
    }
}