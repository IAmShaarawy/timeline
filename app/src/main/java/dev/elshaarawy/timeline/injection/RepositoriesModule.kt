package dev.elshaarawy.timeline.injection

import dev.elshaarawy.timeline.data.repositories.PreferencesRepository
import dev.elshaarawy.timeline.data.repositories.TimelineRepository
import dev.elshaarawy.timeline.data.repositories.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object RepositoriesModule : () -> Module {
    override fun invoke(): Module = module {
        single { PreferencesRepository(androidContext()) }
        factory { UserRepository() }
        factory { TimelineRepository() }
    }
}