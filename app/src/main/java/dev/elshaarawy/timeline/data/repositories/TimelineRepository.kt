package dev.elshaarawy.timeline.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.elshaarawy.timeline.data.entities.Post
import dev.elshaarawy.timeline.data.repositories.TimelineRepository.Companion.PAGES_SIZE
import dev.elshaarawy.timeline.data.sources.TimelineDataSourceFactory
import kotlinx.coroutines.CoroutineScope

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
interface TimelineRepository {

    fun loadTimeline(coroutineScope: CoroutineScope): LiveData<PagedList<Post>>

    companion object : () -> TimelineRepository {
        override fun invoke(): TimelineRepository = TimelineRepositoryImpl()

        const val PAGES_SIZE = 7

    }
}

private class TimelineRepositoryImpl : TimelineRepository {
    override fun loadTimeline(coroutineScope: CoroutineScope): LiveData<PagedList<Post>> {
        val dataSourceFactory = TimelineDataSourceFactory(coroutineScope)
        val pagingConfiguration = PagedList.Config
            .Builder()
            .setPageSize(PAGES_SIZE)
            .setEnablePlaceholders(true)
            .setPrefetchDistance(PAGES_SIZE / 2)
            .build()

        return LivePagedListBuilder(dataSourceFactory, pagingConfiguration).build()
    }
}