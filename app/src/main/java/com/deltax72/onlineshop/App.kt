package com.deltax72.onlineshop

import android.app.Application
import com.deltax72.onlineshop.di.AppModule
import com.deltax72.onlineshop.di.AppModuleImpl

lateinit var appModule: AppModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        appModule = AppModuleImpl(this)
    }
}