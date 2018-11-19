package com.example.guilhermecardoso.magicroutines

import android.app.Application
import com.example.guilhermecardoso.magicroutines.di.components.AppComponent
import com.example.guilhermecardoso.magicroutines.di.components.DaggerAppComponent
import com.example.guilhermecardoso.magicroutines.di.modules.ContextModule

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }
}