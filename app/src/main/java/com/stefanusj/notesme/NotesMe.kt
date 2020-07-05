package com.stefanusj.notesme

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesMe : Application() {

	private val themeRepository: ThemeRepository by inject()

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@NotesMe)
			modules(appModule)
		}

		AppCompatDelegate.setDefaultNightMode(themeRepository.nightMode)
	}
}