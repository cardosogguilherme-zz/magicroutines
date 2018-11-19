package com.example.guilhermecardoso.magicroutines.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @Provides
    fun providesContext(): Context = context

}