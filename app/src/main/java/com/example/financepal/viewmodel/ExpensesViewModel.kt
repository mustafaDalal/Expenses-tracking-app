package com.example.financepal.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.*
import com.example.financepal.data.db.Transaction
import com.example.financepal.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ExpensesViewModel(private val getExpenses: GetExpenses, private val updateExpenses : UpdateExpenses, private val deleteAllExpenses: DeleteAllExpenses, private val updateBudget: UpdateBudget, private val totalBudget : GetBudget) : ViewModel() {

    private val TAG = javaClass.simpleName
    private var _showAddExpenseDialog = mutableStateOf(false)
    val showAddExpenseDialog : State<Boolean> = _showAddExpenseDialog

    private var _showFab = mutableStateOf(true)
    val showFab : State<Boolean> = _showFab

    private var _totalExpense = mutableStateOf(0)
    val totalExpense : State<Int> = _totalExpense

    private var _totalBudgetSet = mutableStateOf(0)
    val totalBudgetSet : State<Int> = _totalBudgetSet

    private var _showUpdateBudgetDialog = mutableStateOf(false)
    val showUpdateBudgetDialog : State<Boolean> = _showUpdateBudgetDialog

    fun setShowUpdateBudgetDialog(value : Boolean) {_showUpdateBudgetDialog.value = value}
    fun setShowAddExpenseDialog(value : Boolean) {_showAddExpenseDialog.value = value}
    fun setShowFab(value : Boolean) {_showFab.value = value}

    private val _expensesList = mutableStateListOf<Transaction>()
    val expensesList: SnapshotStateList<Transaction>
        get() = _expensesList

    init {
        Log.d(TAG, "inside init")
        fetchAllExpenses()
        fetchBudget()
    }

    private fun fetchBudget() {
        viewModelScope.launch {
            _totalBudgetSet.value = totalBudget.execute() ?: 0
        }
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
                calculateTotalExpense(null)
            }
        }
    }

    fun updateExpensesList(transaction: Transaction){
        val job = viewModelScope.launch {
            Log.d(TAG, " updateExpensesList invokeOnCompletion $transaction")
            updateExpenses.execute(transaction)
            expensesList.add(transaction)
            calculateTotalExpense(transaction.amount)
        }

//        job.invokeOnCompletion {
//            _expensesList.add(transaction)
//        }
    }

    private fun calculateTotalExpense(amount: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            var sum = 0;
            if (amount == null) {
                //first time calculation
                for (amt in _expensesList) {
                    sum += amt.amount
                }
                _totalExpense.value = sum
            } else {
                _totalExpense.value += amount
            }
        }
    }

    fun resetData() {
        viewModelScope.launch {
            deleteAllExpenses.execute()
            _expensesList.clear()
            _totalExpense.value = 0
        }
    }
    fun updateBudgetAmt(amount: Int) {
        viewModelScope.launch {
            updateBudget.execute(amount)
        }
    }

//TODO set query in dao to get latest 5 records basis timestamp



}