package br.com.hivecode.notes

import android.app.Application
import br.com.hivecode.notes.di.ModulesDependency
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(listOf(ModulesDependency.appModule)
            )
        }
    }
}