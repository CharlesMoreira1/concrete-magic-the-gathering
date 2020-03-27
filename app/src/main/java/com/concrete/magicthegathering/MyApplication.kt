package com.concrete.magicthegathering

import android.app.Application
import com.concrete.magicthegathering.core.di.addModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(addModule)
        }
    }
}