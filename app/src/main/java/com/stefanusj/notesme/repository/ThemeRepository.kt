package com.stefanusj.notesme.repository

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple data repository for in-app settings.
 */
class ThemeRepository : KoinComponent {

    private val preferences: SharedPreferences by inject { parametersOf(THEME_PREFERENCES) }

    val nightMode: Int
        get() = preferences.getInt(
	        PREFERENCE_THEME_NAME,
	        PREFERENCE_NIGHT_MODE_DEF_VAL
        )

    private val _nightModeLive: MutableLiveData<Int> = MutableLiveData()
    val nightModeLive: LiveData<Int> = _nightModeLive
    var isDarkTheme: Boolean = false
        get() = AppCompatDelegate.MODE_NIGHT_YES == nightMode
        set(value) = preferences.edit {
            putInt(
	            PREFERENCE_THEME_NAME, if (value) {
		            _nightModeLive.value = AppCompatDelegate.MODE_NIGHT_YES
		            AppCompatDelegate.MODE_NIGHT_YES
	            } else {
		            _nightModeLive.value = AppCompatDelegate.MODE_NIGHT_NO
		            AppCompatDelegate.MODE_NIGHT_NO
	            }
            )
            field = value
        }

    init {
        // Init preference LiveData objects.
        _nightModeLive.value = nightMode
    }

    companion object {
        private const val THEME_PREFERENCES = "theme_preferences"

        private const val PREFERENCE_THEME_NAME = "preferences_theme_name"
        private const val PREFERENCE_NIGHT_MODE_DEF_VAL = AppCompatDelegate.MODE_NIGHT_NO
    }
}
