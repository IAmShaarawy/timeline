package dev.elshaarawy.timeline.features.timeline.viewholders

import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.databinding.ItemTimelineVideoBinding
import dev.elshaarawy.timeline.extensions.inflate

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class VideoViewHolder private constructor(private val itemBinding: ItemTimelineVideoBinding) :
    ViewHolder<TimelineItemViewModel>(itemBinding.root) {

    override fun bind(viewModel: TimelineItemViewModel) {
        super.bind(viewModel)
        itemBinding.viewModel = viewModel
    }

    companion object : (ViewGroup, LifecycleOwner) -> VideoViewHolder {
        override fun invoke(parent: ViewGroup, lifecycleOwner: LifecycleOwner): VideoViewHolder =
            VideoViewHolder(parent.inflate(R.layout.item_timeline_video, lifecycleOwner))
    }
}