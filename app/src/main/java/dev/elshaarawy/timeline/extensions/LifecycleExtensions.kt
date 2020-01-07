package dev.elshaarawy.timeline.extensions

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun ViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = viewModelScope.launch(context, start, block)

fun <T> LiveData<T>.shot(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    if (this is MutableLiveData)
        this.observe(lifecycleOwner, Observer {
            it?.also(observer)
                .also { postValue(null) }
        })
    else
        throw IllegalArgumentException("Bullet: this context is not MutableLiveData")
}