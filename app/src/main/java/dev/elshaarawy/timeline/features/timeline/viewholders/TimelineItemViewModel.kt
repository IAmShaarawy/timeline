package dev.elshaarawy.timeline.features.timeline.viewholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.elshaarawy.timeline.data.entities.Post
import dev.elshaarawy.timeline.extensions.launch
import dev.elshaarawy.timeline.features.timeline.TimelineViewModel

/**
 * @author Mohamed Elshaarawy on Jan 03, 2020.
 */
class TimelineItemViewModel(parentViewModel: TimelineViewModel, post: Post?) {
    val text: LiveData<String> = MutableLiveData(post?.text ?: "Loading")
    val img: LiveData<String?> = MutableLiveData(post?.img)
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userImg = MutableLiveData<String>()
    val userImg: LiveData<String> = _userImg

    init {
        parentViewModel.launch {
            post?.userAsync()?.apply {
                _userName.postValue(name)
                _userImg.postValue(img)
            }
        }
    }
}