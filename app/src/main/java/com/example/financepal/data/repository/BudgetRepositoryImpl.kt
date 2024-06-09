package com.example.financepal.data.repository

import android.content.SharedPreferences
import com.example.financepal.domain.repositories.BudgetRepository
import com.example.financepal.utils.AppConstants

class BudgetRepositoryImpl(private val preferences: SharedPreferences) : BudgetRepository {
    override suspend fun getBudget(): Int? {
        return preferences.getInt(AppConstants.KEY_BUDGET, -1)
    }

    override suspend fun updateBudget(newBudget: Int): Int? {
        preferences.edit().putInt(AppConstants.KEY_BUDGET, newBudget).apply()
        return getBudget()
    }
}