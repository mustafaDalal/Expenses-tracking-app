package com.example.financepal.viewmodel

import androidx.*
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.financepal.data.db.Transaction
import com.example.financepal.domain.GetExpenses
import com.example.financepal.domain.UpdateExpenses
import kotlinx.coroutines.launch

class ExpensesViewModel(private val getExpenses: GetExpenses, private val updateExpenses : UpdateExpenses) : ViewModel() {

    private var _showAddExpenseDialog = mutableStateOf(false)
    val showAddExpenseDialog : State<Boolean> = _showAddExpenseDialog

    fun setShowAddExpenseDialog(value : Boolean) {_showAddExpenseDialog.value = value}

    private val _expensesList = MutableLiveData<List<Transaction>>()
    val expensesList: LiveData<List<Transaction>>
        get() = _expensesList

    init {
        fetchAllExpenses()
    }

    private fun fetchAllExpenses() {
        viewModelScope.launch {
            _expensesList.postValue(getExpenses.execute())
        }
    }

    fun updateExpensesList(transaction: Transaction){
        val job = viewModelScope.launch {
            updateExpenses.execute(transaction)
        }
        job.invokeOnCompletion {
            fetchAllExpenses()
        }
    }

}