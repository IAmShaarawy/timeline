package dev.elshaarawy.timeline.data.repositories

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import dev.elshaarawy.timeline.data.entities.Language
import dev.elshaarawy.timeline.data.entities.Language.Companion.from
import dev.elshaarawy.timeline.data.livedata.LanguageLiveData
import dev.elshaarawy.timeline.data.livedata.NightModeLiveData
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository.Companion.APP_LANGUAGE
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository.Companion.NIGHT_MODE
import dev.elshaarawy.timeline.data.repositories.PreferencesRepository.Companion.TIME_LINE_PREFERENCES

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
interface PreferencesRepository {

    var nightMode: Int
    val nightModeLiveData: NightModeLiveData
    var currentLanguage: Language
    val currentLanguageLiveData: LanguageLiveData

    companion object : (Context) -> PreferencesRepository {
        override fun invoke(ctx: Context): PreferencesRepository = PreferencesRepositoryImpl(ctx)

        const val TIME_LINE_PREFERENCES = "time_line_preferences"
        const val NIGHT_MODE = "night_mode"
        const val APP_LANGUAGE = "app_language"
    }
}

private class PreferencesRepositoryImpl(
    ctx: Context
) : PreferencesRepository {
    private val sharedPreferences by lazy {
        ctx.getSharedPreferences(TIME_LINE_PREFERENCES, Context.MODE_PRIVATE)
    }

    override var nightMode: Int
        get() = sharedPreferences.getInt(NIGHT_MODE, AppCompatDelegate.MODE_NIGHT_YES)
        set(value) = sharedPreferences.edit {
            putInt(NIGHT_MODE, value)
        }

    override val nightModeLiveData: NightModeLiveData =
        NightModeLiveData(sharedPreferences, this)

    override var currentLanguage: Language
        get() = from(sharedPreferences.getInt(APP_LANGUAGE, Language.AR.id))
        set(value) {
            sharedPreferences.edit {
                putInt(APP_LANGUAGE, value.id)
            }
        }

    override val currentLanguageLiveData: LanguageLiveData= LanguageLiveData(sharedPreferences, this)
}