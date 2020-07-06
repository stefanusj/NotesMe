package com.stefanusj.notesme.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.stefanusj.notesme.R
import com.stefanusj.notesme.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView<MainActivityBinding>(
			this,
			R.layout.main_activity
		)
	}
}