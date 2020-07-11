package com.stefanusj.notesme.repository

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stefanusj.notesme.R
import com.stefanusj.notesme.model.DarkModeTheme
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * A simple data repository for dark mode theme settings.
 */
class DarkModeThemeRepository: KoinComponent {

	private val preferences: SharedPreferences by inject { parametersOf(DARK_MODE_PREFERENCES) }

	private val darkModeThemes = listOf(
		DarkModeTheme(R.drawable.ic_dark_mode_auto, MODE_NIGHT_FOLLOW_SYSTEM),
		DarkModeTheme(R.drawable.ic_dark_mode_on, MODE_NIGHT_YES),
		DarkModeTheme(R.drawable.ic_dark_mode_off, MODE_NIGHT_NO)
	)

	private val _mode = MutableLiveData<DarkModeTheme>()
	val mode: LiveData<DarkModeTheme> = _mode

	fun switchDarkModeTheme() {
		val oldDarkMode = preferences.getInt(PREFERENCES_MODE_NAME, 0)
		val newDarkMode = oldDarkMode.inc().rem(darkModeThemes.size)
		preferences.edit {
			putInt(PREFERENCES_MODE_NAME, newDarkMode)

			_mode.value = darkModeThemes[newDarkMode]
		}
	}

	init {
		val mode = preferences.getInt(PREFERENCES_MODE_NAME, 0)
		_mode.value = darkModeThemes[mode]
	}

	companion object {
		private const val DARK_MODE_PREFERENCES = "dark_mode_preferences"

		private const val PREFERENCES_MODE_NAME = "mode"
	}
}