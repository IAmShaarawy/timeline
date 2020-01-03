package dev.elshaarawy.timeline.features.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.data.entities.Post
import dev.elshaarawy.timeline.databinding.ItemTimelineBinding

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class TimelineAdapter : PagedListAdapter<Post, TimelineAdapter.TimelineViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate<ItemTimelineBinding>(
            inflater,
            R.layout.item_timeline,
            parent,
            false
        ).let { TimelineViewHolder(it) }
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.bind(currentList?.get(position))
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem

        }
    }

    class TimelineViewHolder(private val item: ItemTimelineBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(post: Post?) {
            item.textView6.text = post?.text ?: "Loading"
        }
    }
}