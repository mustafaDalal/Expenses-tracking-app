package com.example.financepal.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.financepal.data.db.Transaction
import com.example.financepal.viewmodel.ExpensesViewModel
import com.example.myapplication.R

@Composable
fun HomeScreen(navController: NavHostController,
               mViewModel: ExpensesViewModel) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {

        MainScreen(mViewModel)
        if (mViewModel.showAddExpenseDialog.value) {
            AddExpenseDialog(mViewModel)
        }
    }
}

@Composable
private fun MainScreen(mViewModel: ExpensesViewModel) {

//    val amt = mViewModel.totalExpense.value
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(5.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    .background(colorResource(R.color.card_background_90EE90))
            ) {
                Text(
                    text = "Total Expenses",
                    fontSize = 22.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp, 5.dp),
                    style = TextStyle(fontWeight = FontWeight.Black, fontFamily = FontFamily.Monospace)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.wrapContentSize().padding(3.dp),
                        painter = painterResource(R.drawable.rupee_symbol),
                        contentDescription = "rupee symbol"
                    )
                    Text(
                        text = mViewModel.totalExpense.value.toString(),
                        color = Color.Black,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 5.dp)
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight().padding(5.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
                    .background(colorResource(R.color.card_background_90EE90))
            ) {
                Text(
                    text = "Recent Spends",
                    fontSize = 22.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp, 5.dp),
                    style = TextStyle(fontWeight = FontWeight.Black, fontFamily = FontFamily.Monospace)
                )
                LazyColumn(modifier = Modifier.wrapContentSize().fillMaxWidth()) {
                    items(mViewModel.expensesList.reversed().take(5)){
                        RecentTransactionCard(it)
                    }

                }
            }
        }

        ExpensesPieChart(mViewModel)
    }
}

@Composable
private fun ExpensesPieChart(mViewModel: ExpensesViewModel) {

    val totalBudget by mViewModel.totalBudgetSet
    var totalExpenses by remember { mutableStateOf(0f) }

//    val progressSecondary = (totalExpenses / totalBudget).coerceIn(0f, 1f)


    // Simulate a loading process for the expenses (for example purposes)
    LaunchedEffect(Unit) {
        while (totalExpenses <= mViewModel.totalExpense.value) {
            totalExpenses += 100
        }
    }

    val progress = (totalExpenses / totalBudget).coerceIn(0f, 1f)

    Surface(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.wrapContentSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp),
                        strokeWidth = 10.dp,
                        progress = progress,
                        color = Color.Red
//                        amount = totalExpenses,
//                        maxAmount = totalBudget
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Total Expenses: ${mViewModel.totalExpense.value}",
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = "Total Budget: $totalBudget",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
private fun RecentTransactionCard(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight().padding(5.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Row(modifier = Modifier.fillMaxSize()
            .background(colorResource(R.color.card_background_5290EE90)),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.wrapContentSize().padding(3.dp),
                painter = painterResource(R.drawable.rupee_symbol),
                contentDescription = "rupee symbol"
            )
            Text(
                text =transaction.amount.toString(),
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.padding(0.dp, 5.dp, 10.dp, 5.dp)
            )
        }
    }
}

/*
@Composable
fun DrawCircularProgressWithAmount(progress: Float, amount: Float, maxAmount: Float) {
    val size = 200.dp
    val strokeWidth = 10.dp
    val angle = 360 * progress - 90 // Convert progress to angle, start at -90 to make 0% at the top
    val radius = (size.toPx() / 2) - strokeWidth.toPx() / 2
    val x = radius * kotlin.math.cos(Math.toRadians(angle.toDouble())).toFloat() + size.toPx() / 2
    val y = radius * sin(Math.toRadians(angle.toDouble())).toFloat() + size.toPx() / 2

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(size)) {
            drawIntoCanvas { canvas ->
                canvas.drawCircle(
                    color = Color.Gray,
                    radius = radius,
                    center = this.center
                )
                canvas.drawArc(
                    color = Color.Blue,
                    startAngle = -90f,
                    sweepAngle = angle,
                    useCenter = false,
                    topLeft = this.center - radius,
                    size = size.toSize(),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = strokeWidth.toPx()
                    )
                )
            }
        }
        Text(
            text = "${(progress * 100).toInt()}%",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = "$${amount.toInt()}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            style = TextStyle(background = Color.White),
            modifier = Modifier
                .offset(x.toDp() - size / 2, y.toDp() - size / 2)
        )
    }
}
*/

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
