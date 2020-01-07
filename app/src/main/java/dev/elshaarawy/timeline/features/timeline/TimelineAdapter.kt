package dev.elshaarawy.timeline.features.timeline

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.elshaarawy.timeline.data.entities.Post
import dev.elshaarawy.timeline.features.timeline.viewholders.*

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class TimelineAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val timelineViewModel: TimelineViewModel
) : PagedListAdapter<Post, ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            VIEW_TYPE_LOADING -> EmptyViewHolder(parent, lifecycleOwner)
            VIEW_TYPE_TEXT -> TextViewHolder(parent, lifecycleOwner)
            VIEW_TYPE_IMG -> ImgViewHolder(parent, lifecycleOwner)
            VIEW_TYPE_VIDEO -> VideoViewHolder(parent, lifecycleOwner)
            else -> throw IllegalStateException("Not handled state !")
        }


    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as Bindable<TimelineItemViewModel>
        TimelineItemViewModel(
            timelineViewModel,
            currentList?.get(position)
        ).also {
            holder.bind(it)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val post = currentList?.get(position)
        return when {
            post == null -> VIEW_TYPE_LOADING
            post.text != null -> VIEW_TYPE_TEXT
            post.img != null -> VIEW_TYPE_IMG
            post.video != null -> VIEW_TYPE_VIDEO
            else -> throw IllegalStateException("Not handled state !")
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }

        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_VIDEO = 1
        const val VIEW_TYPE_TEXT = 2
        const val VIEW_TYPE_IMG = 3
    }
}