package com.tropfacil
import android.app.Application
import com.tropfacil.di.appModule
import org.koin.android.ext.android.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(this, appModule)
        //AppEventsLogger.activateApp(this);
    }
}