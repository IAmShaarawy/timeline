package dev.elshaarawy.timeline.features.timeline.viewholders

import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.databinding.ItemTimelineImgBinding
import dev.elshaarawy.timeline.extensions.inflate
import dev.elshaarawy.timeline.extensions.shot
import timber.log.Timber

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class ImgViewHolder private constructor(private val itemBinding: ItemTimelineImgBinding) :
    ViewHolder<TimelineItemViewModel>(itemBinding.root) {

    override fun bind(viewModel: TimelineItemViewModel) {
        super.bind(viewModel)
        itemBinding.apply {
            this.viewModel = viewModel
            lifecycleOwner = this@ImgViewHolder
        }
        viewModel.imgClick.shot(this){
            Timber.e("IMG")
        }
    }

    companion object : (ViewGroup) -> ImgViewHolder {
        override fun invoke(parent: ViewGroup): ImgViewHolder =
            ImgViewHolder(parent.inflate(R.layout.item_timeline_img))
    }
}