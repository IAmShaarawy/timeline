package dev.elshaarawy.timeline.features.timeline.viewholders

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.databinding.ItemTimelineEmptyBinding
import dev.elshaarawy.timeline.extensions.inflate

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class EmptyViewHolder private constructor(private val itemBinding: ItemTimelineEmptyBinding) :
    RecyclerView.ViewHolder(itemBinding.root), Bindable<TimelineItemViewModel> {
    override fun bind(viewModel: TimelineItemViewModel) {
        itemBinding.viewModel = viewModel
    }

    override fun unBind() {
        itemBinding.viewModel.close()
    }

    companion object : (ViewGroup, LifecycleOwner?) -> EmptyViewHolder {
        override fun invoke(parent: ViewGroup, lifecycleOwner: LifecycleOwner?): EmptyViewHolder =
            EmptyViewHolder(parent.inflate(R.layout.item_timeline_empty, lifecycleOwner))
    }
}