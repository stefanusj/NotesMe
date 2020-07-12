package com.stefanusj.notesme.repository.model

data class Validation(
	val isValid: Boolean,
	val problem: Int? = null
)