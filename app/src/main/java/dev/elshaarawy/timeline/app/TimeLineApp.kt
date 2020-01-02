package dev.elshaarawy.timeline.app

import android.app.Application
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository
import dev.elshaarawy.timeline.injection.DatabaseModule
import dev.elshaarawy.timeline.injection.RepositoriesModule
import dev.elshaarawy.timeline.injection.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class TimeLineApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        setupKoin(this)
    }

    private fun setupKoin(app: Application) {
        startKoin {
            androidContext(app)
            modules(
                listOf(
                    ViewModelsModule(),
                    RepositoriesModule(),
                    DatabaseModule()
                )
            )
        }
    }
}