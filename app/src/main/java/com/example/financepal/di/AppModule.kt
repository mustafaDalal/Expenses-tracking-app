package com.example.financepal.di

import android.content.Context
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

}