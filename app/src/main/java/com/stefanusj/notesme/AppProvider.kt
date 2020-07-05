package com.stefanusj.notesme

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

fun provideSharedPreferences(application: Application, preferencesName: String): SharedPreferences =
    application.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
