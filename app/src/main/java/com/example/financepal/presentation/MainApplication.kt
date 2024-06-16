package com.example.financepal.presentation

import android.app.Application
import android.content.Context
import com.example.financepal.di.AppModule
import com.example.financepal.di.DaggerMainComponent
import com.example.financepal.di.Injector
import com.example.financepal.di.MainComponent
import com.example.financepal.di.subcomonents.ExpensesViewmodelSubcomponent


class MainApplication : Application(), Injector {

    private lateinit var mainComponent: MainComponent
    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun createExpensesSubcomponent(): ExpensesViewmodelSubcomponent {
        return mainComponent.expensesSubcomponent().create()
    }

}