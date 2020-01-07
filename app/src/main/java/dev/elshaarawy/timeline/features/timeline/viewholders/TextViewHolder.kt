package dev.elshaarawy.timeline.features.timeline.viewholders

import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.databinding.ItemTimelineTextBinding
import dev.elshaarawy.timeline.extensions.inflate

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class TextViewHolder private constructor(private val itemBinding: ItemTimelineTextBinding) :
    ViewHolder<TimelineItemViewModel>(itemBinding.root) {

    override fun bind(viewModel: TimelineItemViewModel) {
        super.bind(viewModel)
        itemBinding.viewModel = viewModel
    }

    companion object : (ViewGroup, LifecycleOwner) -> TextViewHolder {
        override fun invoke(parent: ViewGroup, lifecycleOwner: LifecycleOwner): TextViewHolder =
            TextViewHolder(parent.inflate(R.layout.item_timeline_text, lifecycleOwner))
    }
}