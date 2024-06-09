package com.example.financepal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.*
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.financepal.data.db.Transaction
import com.example.financepal.domain.GetExpenses
import com.example.financepal.domain.UpdateExpenses
import kotlinx.coroutines.launch

class ExpensesViewModel(private val getExpenses: GetExpenses, private val updateExpenses : UpdateExpenses) : ViewModel() {

    val getExpensesList = liveData{
        emit(getExpenses.execute())
    }

    fun updateExpensesList(transaction: Transaction){
        viewModelScope.launch {
            updateExpenses.execute(transaction)
        }
    }

}