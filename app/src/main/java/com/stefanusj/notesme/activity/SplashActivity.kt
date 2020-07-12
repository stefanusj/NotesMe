package com.stefanusj.notesme.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.observe
import com.stefanusj.notesme.repository.AppRepository
import org.koin.android.ext.android.inject

class SplashActivity: AppCompatActivity() {

	private val repository: AppRepository by inject()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		repository.darkModeTheme.mode.observe(this) {
			AppCompatDelegate.setDefaultNightMode(it.mode)
		}

		Intent(this, MainActivity::class.java).also {
			startActivity(it)
			finish()
		}
	}
}