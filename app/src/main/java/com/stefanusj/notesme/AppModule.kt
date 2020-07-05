package com.stefanusj.notesme

import org.koin.dsl.module

@JvmField
val appModule = module {

	single { AppRepository(get()) }

	single { ThemeRepository() }

	single { (name: String) -> provideSharedPreferences(get(), name) }

}