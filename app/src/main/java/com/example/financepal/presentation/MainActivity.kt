package com.example.financepal.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.financepal.data.db.Transaction
import com.example.financepal.di.Injector
import com.example.financepal.ui.theme.MyApplicationTheme
import com.example.financepal.utils.ToastUtils
import com.example.financepal.viewmodel.ExpensesViewModel
import com.example.financepal.viewmodel.ExpensesViewModelFactory
import com.example.myapplication.R
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var mContext: Context
    private var TAG : String = ""

    init {
        TAG = this.javaClass.simpleName
    }

    @Inject
    lateinit var factory: ExpensesViewModelFactory
    private lateinit var expensesViewModel : ExpensesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        (application as Injector).createExpensesSubcomponent().inject(this)

        expensesViewModel = ViewModelProvider(this, factory)
            .get(ExpensesViewModel::class.java)


        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    content = {MainScreen(mContext, expensesViewModel)}
                )
            }
        }

        init()
    }


    private fun init() {

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(mContext: Context, mViewModel: ExpensesViewModel) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {HomeToolbar()},
        content = {ContentScreen(it, mViewModel, mContext)},
        floatingActionButton = {FAB(mContext, mViewModel)} )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeToolbar() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue, titleContentColor = Color.Black),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(7.dp)) { Text(
                text = stringResource(R.string.app_name),
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Black)
            ) }
        }
    )

}

@Composable
private fun FAB(mContext: Context, expensesViewModel: ExpensesViewModel) {
    FloatingActionButton(
        onClick = { showAddExpensesDialog(mContext, expensesViewModel)},
        containerColor = colorResource(R.color.black),
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add expense fab",
                tint = colorResource(R.color.white)
            )
        }
    )
}

fun showAddExpensesDialog(mContext: Context, expensesViewModel: ExpensesViewModel) {
    ToastUtils.showShortToast(mContext = mContext, "show Add expense dialog")

    expensesViewModel.setShowAddExpenseDialog(true)
}

@Composable
private fun ContentScreen(paddingValues: PaddingValues, mViewModel: ExpensesViewModel, mContext: Context) {
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(paddingValues)){

        val expensesList by mViewModel.expensesList.observeAsState(initial = emptyList())
        Text(text = "Expenses ${expensesList.size}", fontSize = 16.sp)

        if(mViewModel.showAddExpenseDialog.value){
            AddExpenseDialog(mViewModel)
        }
    }
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

fun getTransaction(amount: Int): Transaction = Transaction(0, amount, System.currentTimeMillis())

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
