package dev.elshaarawy.timeline.injection

import dev.elshaarawy.timeline.app.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelsModule : () -> Module {
    override fun invoke(): Module = module {
        viewModel { AppViewModel(get()) }
    }

}