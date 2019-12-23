package dev.elshaarawy.timeline.extensions

import androidx.fragment.app.Fragment
import dev.elshaarawy.timeline.app.AppViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
val Fragment.appViewModel: AppViewModel
    get() = sharedViewModel<AppViewModel>().value