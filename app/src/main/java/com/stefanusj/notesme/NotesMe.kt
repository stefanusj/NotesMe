package com.stefanusj.notesme

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.stefanusj.notesme.di.appModule
import com.stefanusj.notesme.repository.DarkModeThemeRepository
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesMe : Application() {

	private val darkModeThemeRepository: DarkModeThemeRepository by inject()

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@NotesMe)
			modules(appModule)
		}

//		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
	}
}