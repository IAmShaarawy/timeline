package dev.elshaarawy.timeline.data.repositories

import android.content.Context
import androidx.core.content.edit
import dev.elshaarawy.timeline.data.entities.Language
import dev.elshaarawy.timeline.data.entities.Language.Companion.from

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
interface PreferencesRepository {

    var isDarkThemeEnabled: Boolean
    var currentLanguage: Language

    companion object : (Context) -> PreferencesRepository {
        override fun invoke(ctx: Context): PreferencesRepository = PreferencesRepositoryImpl(ctx)
    }
}

private class PreferencesRepositoryImpl(
    ctx: Context
) : PreferencesRepository {
    private val sharedPreferences by lazy {
        ctx.getSharedPreferences(TIME_LINE_PREFERENCES, Context.MODE_PRIVATE)
    }

    override var isDarkThemeEnabled: Boolean
        get() = sharedPreferences.getBoolean(IS_DARK_THEME_ENABLED, false)
        set(value) = sharedPreferences.edit {
            putBoolean(IS_DARK_THEME_ENABLED, value)
        }

    override var currentLanguage: Language
        get() = from(sharedPreferences.getInt(APP_LANGUAGE, Language.EN.id))
        set(value) {
            sharedPreferences.edit {
                putInt(APP_LANGUAGE, value.id)
            }
        }

    companion object {
        private const val TIME_LINE_PREFERENCES = "time_line_preferences"
        private const val IS_DARK_THEME_ENABLED = "is_dark_theme_enabled"
        private const val APP_LANGUAGE = "app_language"
    }
}