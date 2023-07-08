package toss.next.drone0898.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import toss.next.drone0898.BuildConfig

@HiltAndroidApp
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}