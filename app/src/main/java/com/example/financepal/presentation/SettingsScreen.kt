package com.example.financepal.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.financepal.viewmodel.ExpensesViewModel

@Composable
fun SettingsScreen(navController: NavHostController, expensesViewModel: ExpensesViewModel) {

    Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.Top,
    modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Text(text = "Settings", fontSize = 16.sp)

    }

}
