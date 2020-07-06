package com.stefanusj.notesme.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.stefanusj.notesme.R

fun provideSharedPreferences(application: Application, preferencesName: String): SharedPreferences =
	application.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

fun provideGoogleSignInOptions(application: Application): GoogleSignInOptions =
	GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
		.requestIdToken(application.getString(R.string.default_web_client_id))
		.requestEmail()
		.requestProfile()
		.build()

fun provideGoogleSignInClient(
	application: Application,
	options: GoogleSignInOptions
): GoogleSignInClient =
	GoogleSignIn.getClient(application, options)