package com.stefanusj.notesme

import android.app.Application
import com.stefanusj.notesme.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NotesMe : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@NotesMe)
			modules(appModule)
		}
	}
}