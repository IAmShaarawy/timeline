package dev.elshaarawy.timeline.injection

import dev.elshaarawy.timeline.app.AppViewModel
import dev.elshaarawy.timeline.features.login.LoginViewModel
import dev.elshaarawy.timeline.features.settings.SettingsViewModel
import dev.elshaarawy.timeline.features.splash.SplashViewModel
import dev.elshaarawy.timeline.features.timeline.TimelineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelsModule : () -> Module {
    override fun invoke(): Module = module {
        viewModel { AppViewModel(get()) }
        viewModel { SplashViewModel() }
        viewModel { LoginViewModel() }
        viewModel { TimelineViewModel() }
        viewModel { SettingsViewModel() }
    }

}