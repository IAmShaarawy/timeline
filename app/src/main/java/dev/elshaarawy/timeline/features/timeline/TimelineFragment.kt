package dev.elshaarawy.timeline.features.timeline

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentTimelineBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class TimelineFragment :
    BaseFragment<FragmentTimelineBinding, TimelineViewModel>(R.layout.fragment_timeline) {
    private val timelineAdapter = TimelineAdapter()
    override val viewModel: TimelineViewModel by viewModel()

    override fun FragmentTimelineBinding.setupUI() {

    }

    override fun TimelineViewModel.observeViewModel() {
        timelineLiveData.observe(viewLifecycleOwner, Observer {
            timelineAdapter.submitList(it)
        })
    }
}