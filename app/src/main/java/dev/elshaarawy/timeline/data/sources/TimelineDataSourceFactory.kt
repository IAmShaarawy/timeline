package dev.elshaarawy.timeline.data.sources

import androidx.paging.DataSource
import dev.elshaarawy.timeline.data.entities.Post
import kotlinx.coroutines.CoroutineScope

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class TimelineDataSourceFactory(private val coroutineScope: CoroutineScope) :
    DataSource.Factory<Post, Post>() {
    override fun create(): DataSource<Post, Post> =
        TimelineDataSource(coroutineScope)
}