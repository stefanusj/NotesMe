package com.stefanusj.notesme.helper

import com.stefanusj.notesme.R
import com.stefanusj.notesme.model.Validation

object Validator {

	fun onNoteAddValidation(
		title: String?,
		text: String?,
		color: Int?
	): Validation {
		if (title.isNullOrBlank()) return Validation(false, R.string.title_empty)
		if (text.isNullOrBlank()) return Validation(false, R.string.text_empty)
		if (color.isNullOrZero()) return Validation(false, R.string.color_empty)
		return Validation(true)
	}

	fun onNoteEditValidation(
		id: String?,
		title: String?,
		text: String?,
		color: Int?
	): Validation {
		if (id.isNullOrBlank()) return Validation(false, R.string.title_empty)
		if (title.isNullOrBlank()) return Validation(false, R.string.title_empty)
		if (text.isNullOrBlank()) return Validation(false, R.string.text_empty)
		if (color.isNullOrZero()) return Validation(false, R.string.color_empty)
		return Validation(true)
	}
}