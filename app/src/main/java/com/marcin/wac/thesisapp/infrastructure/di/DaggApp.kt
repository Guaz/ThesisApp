package com.marcin.wac.thesisapp.infrastructure.di

import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho

class DaggApp : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        Stetho.initializeWithDefaults(this)
    }
}