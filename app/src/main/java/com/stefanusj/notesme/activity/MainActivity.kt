package com.stefanusj.notesme.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.setDefaultNightMode
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.observe
import com.stefanusj.notesme.R
import com.stefanusj.notesme.databinding.MainActivityBinding
import com.stefanusj.notesme.repository.AppRepository
import org.koin.android.ext.android.inject

class MainActivity: AppCompatActivity() {

	private val repository: AppRepository by inject()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView<MainActivityBinding>(this, R.layout.main_activity)

		repository.darkModeTheme.mode.observe(this) {
			setDefaultNightMode(it.mode)
		}
	}
}