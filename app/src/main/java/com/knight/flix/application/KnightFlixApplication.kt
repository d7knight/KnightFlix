package com.knight.flix.application

import android.app.Application
import com.knight.flix.injection.AppComponent
import com.knight.flix.injection.DaggerAppComponent
import com.knight.flix.usecase.LoadConfiguration
import javax.inject.Inject
import timber.log.Timber

class KnightFlixApplication : Application() {

    private lateinit var appComponent: AppComponent
    @Inject
    lateinit var loadConfiguration: LoadConfiguration

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        loadConfiguration()
    }

    fun getAppComponent() = appComponent
}