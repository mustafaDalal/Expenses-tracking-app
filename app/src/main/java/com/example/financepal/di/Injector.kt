package com.example.financepal.di

import com.example.financepal.di.subcomonents.ExpensesViewmodelSubcomponent

interface Injector {

    fun createExpensesSubcomponent() : ExpensesViewmodelSubcomponent
}