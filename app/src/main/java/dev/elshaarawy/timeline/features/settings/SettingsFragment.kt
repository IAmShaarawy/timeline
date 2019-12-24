package dev.elshaarawy.timeline.features.settings

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentSettingsBinding
import dev.elshaarawy.timeline.extensions.appViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class SettingsFragment :
    BaseFragment<FragmentSettingsBinding, SettingsViewModel>(R.layout.fragment_settings) {
    override val viewModel: SettingsViewModel by viewModel{ parametersOf(appViewModel)}
    override fun SettingsViewModel.observeViewModel() {
        navigateToLogin.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToLoginFragment())
        })
    }
}