package com.example.financepal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financepal.domain.*

class ExpensesViewModelFactory(private val getExpenses: GetExpenses,
                               private val updateExpenses : UpdateExpenses,
                               private val deleteAllExpenses: DeleteAllExpenses, private val updateBudget: UpdateBudget,
    private val getBudget: GetBudget
                               ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpensesViewModel(getExpenses, updateExpenses, deleteAllExpenses, updateBudget, getBudget) as T
    }
}