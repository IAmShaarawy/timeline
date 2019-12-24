package dev.elshaarawy.timeline.features.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.elshaarawy.timeline.data.repositories.UserRepository
import dev.elshaarawy.timeline.extensions.launch
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import timber.log.Timber

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val phone = MutableLiveData<String>("")
}