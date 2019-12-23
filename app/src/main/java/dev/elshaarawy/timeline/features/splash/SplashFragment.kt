package dev.elshaarawy.timeline.features.splash

import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class SplashFragment() :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    override val viewModel: SplashViewModel by viewModel()
}