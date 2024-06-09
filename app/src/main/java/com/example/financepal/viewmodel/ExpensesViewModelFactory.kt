package com.example.financepal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financepal.domain.GetExpenses
import com.example.financepal.domain.UpdateExpenses

class ExpensesViewModelFactory(private val getExpenses: GetExpenses,
                               private val updateExpenses : UpdateExpenses) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExpensesViewModel(getExpenses, updateExpenses) as T
    }
}