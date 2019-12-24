package dev.elshaarawy.timeline.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.elshaarawy.timeline.data.repositories.UserRepository
import dev.elshaarawy.timeline.extensions.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class SplashViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _navigateToLogin = MutableLiveData<Unit>()
    val navigateToLogin: LiveData<Unit> = _navigateToLogin

    private val _navigateToTimeline = MutableLiveData<Unit>()
    val navigateToTimeline: LiveData<Unit> = _navigateToTimeline

    init {
        launch {
            delay(2000)
            if (userRepository.isLoggedIn()) {
                _navigateToTimeline.postValue(Unit)
            } else {
                _navigateToLogin.postValue(Unit)
            }
        }
    }
}