package com.example.financepal.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.financepal.viewmodel.ExpensesViewModel
import com.example.myapplication.R


private const val TAG = "SettingsScreen"
@Composable
fun SettingsScreen(navController: NavHostController, expensesViewModel: ExpensesViewModel) {

    Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.Top,
    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(5.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight().padding(5.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {

            LazyColumn(modifier = Modifier.background(colorResource(R.color.card_background_90EE90))
                .fillMaxWidth().wrapContentHeight()){

                items(getTotalRows()){
                    SettingsItem(it, expensesViewModel)
                }
            }
        }

        if (expensesViewModel.showUpdateBudgetDialog.value) {
            UpdateBudgetDialog(expensesViewModel)
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UpdateBudgetDialog(mViewModel: ExpensesViewModel) {
    var amount by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {onDismiss(amount, mViewModel)},
        title = {
            Text(text = "Enter Budget Amount")
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
    amount.takeIf { !it.isNullOrEmpty() }?.let {
        val amountInInteger = Integer.parseInt(it)
        if(amountInInteger != 0){
            mViewModel.updateBudgetAmt(amountInInteger)
        }
    }
    mViewModel.setShowUpdateBudgetDialog(false)
}

@Composable
private fun SettingsItem(settingRow: SettingRow, mViewModel: ExpensesViewModel) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight().padding(5.dp)
            .clickable {
                when (settingRow.id) {
                    0 -> mViewModel.setShowUpdateBudgetDialog(true)/*updateBudgetF(updateBudget)*/
                    1 -> mViewModel.resetData()
                    else -> {
                        Log.d(TAG, "invalid id ")
                    }
                }
            },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Row(modifier = Modifier.fillMaxSize()
            .background(colorResource(R.color.card_background_5290EE90)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                style = TextStyle(fontWeight = FontWeight.Black, fontFamily = FontFamily.Monospace),
                text =settingRow.title.toString(),
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.padding(10.dp, 5.dp)
            )
            Image(modifier = Modifier.wrapContentSize(),
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = "right_arrow")
        }
    }
}

fun getTotalRows() = listOf(
    SettingRow(0, "Update Budget"),
    SettingRow(1, "Reset Expenses"))

data class SettingRow(val id : Int, val title : String)
