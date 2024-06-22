package com.example.financepal.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.financepal.data.db.Transaction
import com.example.financepal.viewmodel.ExpensesViewModel

@Composable
fun HomeScreen(navController: NavHostController,
               mViewModel: ExpensesViewModel) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {

        Text(text = "Expenses ${mViewModel.expensesList.size}", fontSize = 16.sp)

        if (mViewModel.showAddExpenseDialog.value) {
            AddExpenseDialog(mViewModel)
        }
    }
}
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AddExpenseDialog(mViewModel: ExpensesViewModel) {
        var amount by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = {onDismiss(amount, mViewModel)},
            title = {
                Text(text = "Enter Amount")
            },
            text = {
                Column {
                    TextField(
                        value = amount,
                        onValueChange = { newAmount ->
                            if (newAmount.all { it.isDigit() }) {
                                amount = newAmount
                            }
                        },
                        label = { Text("Amount") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDismiss(amount, mViewModel)
                    }
                ) {
                    Text("Submit")
                }
            },
            dismissButton = {
                Button(
                    onClick = {onDismiss(null, mViewModel)}
                ) {
                    Text("Cancel")
                }
            }
        )

    }

    private fun onDismiss(amount: String?, mViewModel: ExpensesViewModel) {
        Log.d("MainActivity", "amount : $amount")
        amount.takeIf { !it.isNullOrEmpty() }?.let {
            val amountInInteger = Integer.parseInt(it)
            if(amountInInteger != 0){
                mViewModel.updateExpensesList(getTransaction(amountInInteger))
            }
        }
        mViewModel.setShowAddExpenseDialog(false)
    }

    private fun getTransaction(amount: Int): Transaction = Transaction(amount = amount, timestamp = System.currentTimeMillis())
