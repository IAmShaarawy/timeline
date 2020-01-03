package dev.elshaarawy.timeline.data.sources

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import dev.elshaarawy.timeline.data.entities.Post
import dev.elshaarawy.timeline.extensions.getAsync
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class TimelineDataSource(private val coroutineScope: CoroutineScope) :
    PageKeyedDataSource<Post, Post>() {

    private val timelineCollectionRef by lazy {
        FirebaseFirestore.getInstance().collection(TIMELINE_COLLECTION)
    }

    override fun loadInitial(
        params: LoadInitialParams<Post>,
        callback: LoadInitialCallback<Post, Post>
    ) {
        coroutineScope.launch(CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
            callback.onRetryableError(
                throwable
            )
        }) {
            timelineCollectionRef.limit(params.requestedLoadSize.toLong())
                .getAsync()
                .documents
                .map { it.toObject(Post::class.java)!!.withId<Post>(it.id) }
                .also { callback.onResult(it, 0, it.count(), it.firstOrNull(), it.lastOrNull()) }
        }
    }

    override fun loadAfter(
        params: LoadParams<Post>,
        callback: LoadCallback<Post, Post>
    ) {
        coroutineScope.launch(CoroutineExceptionHandler { _, throwable ->
            callback.onRetryableError(
                throwable
            )
        }) {
            timelineCollectionRef.limit(params.requestedLoadSize.toLong())
                .startAfter(params.key.id)
                .getAsync()
                .documents
                .map { it.toObject(Post::class.java)!!.withId<Post>(it.id) }
                .also { callback.onResult(it, it.firstOrNull()) }
        }
    }

    override fun loadBefore(
        params: LoadParams<Post>,
        callback: LoadCallback<Post, Post>
    ) = Unit

    companion object {
        private const val TIMELINE_COLLECTION = "timeline"
    }
}