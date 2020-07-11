package com.stefanusj.notesme.model

import androidx.annotation.DrawableRes

data class DarkModeTheme(
	@DrawableRes val icon: Int,
	val mode: Int
)