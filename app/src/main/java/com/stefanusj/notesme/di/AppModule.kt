package com.stefanusj.notesme.di

import com.stefanusj.notesme.repository.AppRepository
import com.stefanusj.notesme.repository.ThemeRepository
import org.koin.dsl.module

@JvmField
val appModule = module {

	single { AppRepository(get()) }

	single { ThemeRepository() }

	single { (name: String) ->
		provideSharedPreferences(
			get(),
			name
		)
	}

	factory { provideGoogleSignInOptions(get()) }

	factory { provideGoogleSignInClient(get(), get()) }

}