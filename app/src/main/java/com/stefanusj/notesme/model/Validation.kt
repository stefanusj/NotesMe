package com.stefanusj.notesme.model

data class Validation(
	val isValid: Boolean,
	val problem: Int? = null
)