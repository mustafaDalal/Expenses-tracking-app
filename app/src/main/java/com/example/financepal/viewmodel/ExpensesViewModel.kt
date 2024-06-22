package com.example.financepal.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.*
import com.example.financepal.data.db.Transaction
import com.example.financepal.domain.GetExpenses
import com.example.financepal.domain.UpdateExpenses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ExpensesViewModel(private val getExpenses: GetExpenses, private val updateExpenses : UpdateExpenses) : ViewModel() {

    private val TAG = javaClass.simpleName
    private var _showAddExpenseDialog = mutableStateOf(false)
    val showAddExpenseDialog : State<Boolean> = _showAddExpenseDialog

    private var _showFab = mutableStateOf(true)
    val showFab : State<Boolean> = _showFab

    fun setShowAddExpenseDialog(value : Boolean) {_showAddExpenseDialog.value = value}
    fun setShowFab(value : Boolean) {_showFab.value = value}

    private val _expensesList = mutableStateListOf<Transaction>()
    val expensesList: SnapshotStateList<Transaction>
        get() = _expensesList

    init {
        Log.d(TAG, "inside init")
        fetchAllExpenses()
    }

    private fun fetchAllExpenses() {
        viewModelScope.launch {
            getExpenses.execute()?.flowOn(Dispatchers.IO)
            ?.catch {
                // handle exception
                Log.d(TAG, " fetchAllExpenses collect ${it.message}")
            }
            ?.collect {
                Log.d(TAG, " fetchAllExpenses collect ${it.size}")
                expensesList.clear()
                expensesList.addAll(it)
            }
        }
    }

    fun updateExpensesList(transaction: Transaction){
        val job = viewModelScope.launch {
            Log.d(TAG, " updateExpensesList invokeOnCompletion $transaction")
            updateExpenses.execute(transaction)
            expensesList.add(transaction)
        }
//        job.invokeOnCompletion {
//            _expensesList.add(transaction)
//        }
    }

}