package com.example.financepal.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.financepal.ui.theme.MyApplicationTheme
import com.example.financepal.utils.NavigationItem
import com.example.financepal.utils.ToastUtils
import com.example.financepal.viewmodel.ExpensesViewModel
import com.example.financepal.viewmodel.ExpensesViewModelFactory
import com.example.myapplication.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var TAG : String = ""

    init {
        TAG = this.javaClass.simpleName
    }

    @Inject
    lateinit var factory: ExpensesViewModelFactory
    private lateinit var expensesViewModel : ExpensesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        expensesViewModel = ViewModelProvider(this, factory)
            .get(ExpensesViewModel::class.java)


        setContent {
            MyApplicationTheme() {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    content = {MainScreen(this)}
                )
            }
        }

        init()
    }


    private fun init() {

    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(mContext: Context) {

    val navController = rememberNavController()
    val showFab by expensesViewModel.showFab
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {HomeToolbar()},
        bottomBar = { BottomAppBar(navController) },
        floatingActionButton = {
            if (showFab) {
                FAB(mContext)
            }
        }){ paddingValues ->
        AppNavHost(paddingValues = paddingValues, navController = navController)
    }
}

    @Composable
    private fun BottomAppBar(navController: NavHostController) {
        NavigationBar {
            val currentRoute = currentRoute(navController)
            getListOfScreens().forEach { screen ->
                NavigationBarItem(
                    icon = { Icon(screen.icon, contentDescription = null) },
                    label = { Text(screen.route) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to avoid building up a large stack of destinations
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun currentRoute(navController: NavHostController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route
    }

@Composable
private fun AppNavHost(paddingValues: PaddingValues,
                       modifier : Modifier = Modifier,
                       navController: NavHostController,
                       startDestination: String = NavigationItem.Home.route) {
    NavHost(
        modifier = modifier.padding(paddingValues),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            expensesViewModel.setShowFab(true)
            HomeScreen(navController, expensesViewModel)
        }
        composable(NavigationItem.Settings.route) {
            expensesViewModel.setShowFab(false)
            SettingsScreen(navController, expensesViewModel)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeToolbar() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(R.color.toolbar_color), titleContentColor = Color.Black),
        title = {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(7.dp)) { Text(
                text = stringResource(R.string.app_name),
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Black)
            ) }
        }
    )

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
    ToastUtils.showShortToast(mContext = mContext, "show Add expense dialog")

    expensesViewModel.setShowAddExpenseDialog(true)
}

private fun getListOfScreens() =  listOf(
    NavigationItem.Home,
    NavigationItem.Settings)
}
