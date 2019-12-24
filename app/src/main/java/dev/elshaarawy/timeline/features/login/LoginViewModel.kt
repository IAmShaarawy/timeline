package dev.elshaarawy.timeline.features.login

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
    val verificationCode = MutableLiveData<String>("")

    private val _isVerificationVisible = MutableLiveData<Boolean>(false)
    val isVerificationVisible: LiveData<Boolean> = _isVerificationVisible

    private val _navigateToTimelineChannel = Channel<Unit>()
    val navigateToTimelineFlow: Flow<Unit> = _navigateToTimelineChannel.consumeAsFlow()

    fun login() {
        launch(CoroutineExceptionHandler { _, thr -> Timber.e(thr) }) {
            val user = userRepository.login(phone.value!!)
            if (user == null) {
                _isVerificationVisible.postValue(true)
            } else {
                _navigateToTimelineChannel.send(Unit)
            }
        }
    }

    fun verify() {
        launch(CoroutineExceptionHandler { _, thr -> Timber.e(thr) }) {
            userRepository.verify(verificationCode.value!!)
            _navigateToTimelineChannel.send(Unit)
        }
    }
}