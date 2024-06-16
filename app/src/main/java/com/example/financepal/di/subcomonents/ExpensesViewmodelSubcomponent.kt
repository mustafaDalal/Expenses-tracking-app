package com.example.financepal.di.subcomonents

import com.example.financepal.presentation.MainActivity
import dagger.Subcomponent

@ExpensesScope
@Subcomponent(modules = [ExpensesViewmodelModule::class])
interface ExpensesViewmodelSubcomponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create() : ExpensesViewmodelSubcomponent
    }

}