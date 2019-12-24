package dev.elshaarawy.timeline.features.splash

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentSplashBinding
import dev.elshaarawy.timeline.extensions.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    override val viewModel: SplashViewModel by viewModel()
    override fun SplashViewModel.observeViewModel() {
        navigateToLogin.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        })
        navigateToTimeline.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToTimelineFragment())
        })
    }
}