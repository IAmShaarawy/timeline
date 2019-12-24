package dev.elshaarawy.timeline.app

import androidx.appcompat.app.AppCompatDelegate.NightMode
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.elshaarawy.timeline.R
import dev.elshaarawy.timeline.data.entities.Language
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository
import dev.elshaarawy.timeline.extensions.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class AppViewModel(private val preferencesRepository: PreferencesRepository) : ViewModel() {

    val nightMode: LiveData<Int> = preferencesRepository.nightModeLiveData

    private val _recreateViewChannel = Channel<Unit>()
    val recreateViewChannel: ReceiveChannel<Unit> = _recreateViewChannel

    private val _isToolbarVisible = MutableLiveData<Boolean>(false)
    val isToolbarVisible: LiveData<Boolean> = _isToolbarVisible

    private val _isBottomNavViewVisible = MutableLiveData<Boolean>(false)
    val isBottomNavViewVisible: LiveData<Boolean> = _isBottomNavViewVisible

    fun changeNighMode(@NightMode mode: Int) {
        preferencesRepository.nightMode = mode
    }

    fun changeLan(lang: Language) {
        preferencesRepository.currentLanguage = lang
        launch {
            _recreateViewChannel.send(Unit)
        }
    }

    fun onDestinationChange(id: Int) {
        decideToolbarVisibility(id)
        decideBottomNavViewVisibility(id)
    }

    private fun decideToolbarVisibility(id: Int) {
        when (id) {
            R.id.splashFragment,
            R.id.loginFragment -> false
            else -> true
        }.also { _isToolbarVisible.postValue(it) }
    }

    private fun decideBottomNavViewVisibility(id: Int) {
        when (id) {
            R.id.splashFragment,
            R.id.loginFragment -> false
            else -> true
        }.also { _isToolbarVisible.postValue(it) }
    }
}