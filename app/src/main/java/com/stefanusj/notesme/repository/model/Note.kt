package com.stefanusj.notesme.repository.model

import com.google.firebase.firestore.Exclude

data class Note(
	@set:Exclude
	@get:Exclude
	var id: String = "",
	val userId: String = "",
	var color: Int = 0,
	var title: String = "",
	var text: String = "",
	var lastModified: Long = 0
)