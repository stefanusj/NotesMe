package com.stefanusj.notesme

import android.app.Application
import org.koin.core.KoinComponent

class AppRepository(application: Application): KoinComponent {

	private val app = application as NotesMe

}