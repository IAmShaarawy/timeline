package dev.elshaarawy.timeline.data.livedata

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.lifecycle.LiveData
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class NightModeLiveData(
    private val pref: SharedPreferences,
    private val preferencesRepository: PreferencesRepository
) : LiveData<Int>(preferencesRepository.nightMode) {

    private val nighModeListener = OnSharedPreferenceChangeListener { _, key ->
        if (key == PreferencesRepository.NIGHT_MODE) {
            postValue(preferencesRepository.nightMode)
        }
    }

    override fun onActive() {
        super.onActive()
        pref.registerOnSharedPreferenceChangeListener(nighModeListener)
    }

    override fun onInactive() {
        super.onInactive()
        pref.unregisterOnSharedPreferenceChangeListener(nighModeListener)
    }
}