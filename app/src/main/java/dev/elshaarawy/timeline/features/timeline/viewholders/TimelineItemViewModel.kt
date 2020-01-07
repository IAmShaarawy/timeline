package dev.elshaarawy.timeline.features.timeline.viewholders

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.elshaarawy.timeline.data.entities.Post
import dev.elshaarawy.timeline.extensions.launch
import dev.elshaarawy.timeline.features.timeline.TimelineViewModel
import kotlinx.coroutines.*
import java.io.Closeable

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class TimelineItemViewModel(parentViewModel: TimelineViewModel, private val post: Post?) :
    Closeable {
    val text: LiveData<String?> = MutableLiveData(post?.text)
    val img: LiveData<String?> = MutableLiveData(post?.img)
    private val _video = MutableLiveData<Bitmap>()
    val video: LiveData<Bitmap> = _video

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userImg = MutableLiveData<String>()
    val userImg: LiveData<String> = _userImg

    private val _imgClick = MutableLiveData<String>()
    val imgClick: LiveData<String> = _imgClick

    private val _videoClick = MutableLiveData<String>()
    val videoClick: LiveData<String> = _videoClick

    init {
        parentViewModel.launch {
            listOf(loadUserAsync(post), loadVideoThumbnailAsync(post)).awaitAll()
        }
    }

    private fun CoroutineScope.loadUserAsync(post: Post?): Deferred<Unit> = async {
        post?.userAsync()?.apply {
            _userName.postValue(name)
            _userImg.postValue(img)
        }
        Unit
    }

    private fun CoroutineScope.loadVideoThumbnailAsync(post: Post?): Deferred<Unit> =
        async(Dispatchers.IO) {
            post?.video?.also {
                MediaMetadataRetriever().run {
                    setDataSource(it, HashMap<String, String>())
                    getFrameAtTime(0)
                }.let(_video::postValue)
            }
            Unit
        }

    fun onImageClick() = _imgClick.postValue(post?.img)
    fun onVideoClick() = _videoClick.postValue(post?.video)
    override fun close() {

    }
}