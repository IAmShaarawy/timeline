package dev.elshaarawy.timeline.features.login

import androidx.navigation.fragment.findNavController
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentLoginBinding
import dev.elshaarawy.timeline.extensions.launch
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val viewModel: LoginViewModel by viewModel()
    override fun LoginViewModel.observeViewModel() {
        launch {
            navigateToTimelineFlow.collect {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToTimelineFragment()
                )
            }
        }
    }
}