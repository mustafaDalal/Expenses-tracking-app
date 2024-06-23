package com.example.financepal.di

import android.content.Context
import android.content.SharedPreferences
import com.example.financepal.di.subcomonents.ExpensesViewmodelSubcomponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
subcomponents = [ExpensesViewmodelSubcomponent::class]
)
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesApplicationContext() : Context {
        return context.applicationContext
    }

    @Provides
    @Singleton
    fun providesSharedPrefs(context: Context) : SharedPreferences {
        return context.getSharedPreferences("budget", Context.MODE_PRIVATE)
    }

}