package dev.elshaarawy.timeline.features.timeline.viewholders

import android.content.Intent
import android.net.Uri
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.Bindable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.databinding.ItemTimelineVideoBinding
import dev.elshaarawy.timeline.extensions.inflate
import dev.elshaarawy.timeline.extensions.shot
import timber.log.Timber

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class VideoViewHolder private constructor(private val itemBinding: ItemTimelineVideoBinding) :
    ViewHolder<TimelineItemViewModel>(itemBinding.root) {

    override fun bind(viewModel: TimelineItemViewModel) {
        super.bind(viewModel)
        itemBinding.apply {
            this.viewModel = viewModel
            lifecycleOwner = this@VideoViewHolder
        }

        viewModel.videoClick.shot(this) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            intent.setDataAndType(Uri.parse(it), "video/mp4")
            itemBinding.root.context.startActivity(intent)
        }
    }

    companion object : (ViewGroup) -> VideoViewHolder {
        override fun invoke(parent: ViewGroup): VideoViewHolder =
            VideoViewHolder(parent.inflate(R.layout.item_timeline_video))
    }
}