package com.example.financepal.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
                    content = {MainScreen(mContext, expensesViewModel.getExpensesList.value)}
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
private fun MainScreen(mContext: Context, expensesViewModel: List<Transaction>?) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        content = {ContentScreen(it, expensesViewModel, mContext)},
        floatingActionButton = {FAB(mContext)} )
}

@Composable
private fun FAB(mContext: Context) {
    FloatingActionButton(
        onClick = { showAddExpensesDialog(mContext)},
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

fun showAddExpensesDialog(mContext: Context) {

    ToastUtils.showLongToast(mContext = mContext, "show Add expense dialog")
}

@Composable
private fun ContentScreen(paddingValues: PaddingValues, mViewModel: List<Transaction>?, mContext: Context) {
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(paddingValues)){

        Text(text = "Expenses ${mViewModel?.size ?: 0}", fontSize = 16.sp)
    }
}