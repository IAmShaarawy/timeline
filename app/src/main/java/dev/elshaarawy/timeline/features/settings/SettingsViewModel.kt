package dev.elshaarawy.timeline.features.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.elshaarawy.timeline.app.AppViewModel
import dev.elshaarawy.timeline.data.entities.Language
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository
import dev.elshaarawy.timeline.data.repositories.UserRepository
import dev.elshaarawy.timeline.extensions.launch

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class SettingsViewModel(
    private val appViewModel: AppViewModel,
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Unit>()
    val navigateToLogin: LiveData<Unit> = _navigateToLogin

    fun switchLang() {
        val langToChangeTo = if (preferencesRepository.currentLanguage == Language.AR)
            Language.EN
        else
            Language.AR
        appViewModel.changeLan(langToChangeTo)
    }

    fun switchTheme() {
        val modeToChangeTo =
            if (preferencesRepository.nightMode == AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.MODE_NIGHT_NO
            else
                AppCompatDelegate.MODE_NIGHT_YES
        appViewModel.changeNighMode(modeToChangeTo)
    }

    fun logout() {
        launch {
            userRepository.logOut()
            _navigateToLogin.postValue(Unit)
        }
    }
}