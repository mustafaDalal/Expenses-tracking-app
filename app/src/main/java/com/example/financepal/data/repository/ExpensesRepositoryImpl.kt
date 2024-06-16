package com.example.financepal.data.repository

import com.example.financepal.data.db.ExpensesLocalDatabase
import com.example.financepal.data.db.ExpensesLocalDatabaseImpl
import com.example.financepal.data.db.Transaction
import com.example.financepal.domain.repositories.ExpensesRepository

class ExpensesRepositoryImpl(private val localDatabaseImpl: ExpensesLocalDatabase) : ExpensesRepository {
    override suspend fun getTotalExpenses(): List<Transaction>? {
        return localDatabaseImpl.getTotalExpenses()
    }

    override suspend fun updateTotalExpenses(transaction: Transaction) {
        localDatabaseImpl.updateTotalExpenses(transaction)
    }

    override suspend fun deleteExpense(amount: Int, timestamp: Long) {
        localDatabaseImpl.deleteExpense(amount, timestamp)
    }

    override suspend fun deleteAllExpenses() {
        localDatabaseImpl.deleteAllExpenses()
    }
}