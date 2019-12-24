package dev.elshaarawy.timeline.features.splash

import androidx.navigation.fragment.findNavController
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentSplashBinding
import dev.elshaarawy.timeline.extensions.launch
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class SplashFragment :
    BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {
    override val viewModel: SplashViewModel by viewModel()
    override fun SplashViewModel.observeViewModel() {
        launch {
            navigateToLoginFlow.collect {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
        }
        launch {
            navigateToTimelineFlow.collect {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToTimelineFragment())
            }
        }
    }
}