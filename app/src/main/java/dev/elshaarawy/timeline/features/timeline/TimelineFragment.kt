package dev.elshaarawy.timeline.features.timeline

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.base.BaseFragment
import dev.elshaarawy.timeline.databinding.FragmentTimelineBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class TimelineFragment :
    BaseFragment<FragmentTimelineBinding, TimelineViewModel>(R.layout.fragment_timeline) {
    private val timelineAdapter = TimelineAdapter()
    override val viewModel: TimelineViewModel by viewModel()

    override fun FragmentTimelineBinding.setupUI() {
        rvTimeline.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = timelineAdapter
        }
    }

    override fun TimelineViewModel.observeViewModel() {
        timelineLiveData.observe(viewLifecycleOwner, Observer {
            timelineAdapter.submitList(it)
        })
    }
}