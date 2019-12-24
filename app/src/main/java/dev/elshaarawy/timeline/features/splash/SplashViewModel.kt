package dev.elshaarawy.timeline.features.splash

import androidx.lifecycle.ViewModel
import dev.elshaarawy.timeline.data.repositories.UserRepository
import dev.elshaarawy.timeline.extensions.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class SplashViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _navigateToLoginChannel = Channel<Unit>()
    val navigateToLoginFlow: Flow<Unit> = _navigateToLoginChannel.consumeAsFlow()

    private val _navigateToTimelineChannel = Channel<Unit>()
    val navigateToTimelineFlow: Flow<Unit> = _navigateToTimelineChannel.consumeAsFlow()

    init {
        launch {
            delay(2000)
            if (userRepository.isLoggedIn()) {
                _navigateToTimelineChannel.send(Unit)
            } else {
                _navigateToLoginChannel.send(Unit)
            }
        }
    }
}