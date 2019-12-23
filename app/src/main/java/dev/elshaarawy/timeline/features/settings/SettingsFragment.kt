package dev.elshaarawy.timeline.features.settings

import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentSettingsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class SettingsFragment :
    BaseFragment<FragmentSettingsBinding, SettingsViewModel>(R.layout.fragment_settings) {
    override val viewModel: SettingsViewModel by viewModel()
}