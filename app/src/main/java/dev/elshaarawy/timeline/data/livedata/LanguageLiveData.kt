package dev.elshaarawy.timeline.data.livedata

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.lifecycle.LiveData
import dev.elshaarawy.timeline.data.entities.Language
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class LanguageLiveData(
    private val pref: SharedPreferences,
    private val preferencesRepository: PreferencesRepository
) : LiveData<Language>(preferencesRepository.currentLanguage) {
    private val nighModeListener = OnSharedPreferenceChangeListener { _, key ->
        if (key == PreferencesRepository.APP_LANGUAGE) {
            postValue(preferencesRepository.currentLanguage)
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