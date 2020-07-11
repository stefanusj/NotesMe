package com.stefanusj.notesme.ui.note.edit

import android.app.Application
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.navigation.findNavController
import com.stefanusj.notesme.R
import com.stefanusj.notesme.helper.Validator.onNoteDeleteValidation
import com.stefanusj.notesme.helper.Validator.onNoteEditValidation
import com.stefanusj.notesme.ui.BaseViewModel

class NoteEditViewModel(application: Application): BaseViewModel(application) {

	private val _id = MutableLiveData<String>()

	private val _note = _id.switchMap { liveData { emit(repository.getNote(it)) } }

	val title = MediatorLiveData<String>()
	val text = MediatorLiveData<String>()
	val color = MediatorLiveData<Int>()

	init {
		title.addSource(_note) { title.value = it.title }
		text.addSource(_note) { text.value = it.text }
		color.addSource(_note) { color.value = it.color }
	}

	fun setNote(id: String) {
		_id.value = id
	}

	fun onColorSet(color: Int) {
		this.color.value = color
	}

	fun onBackClicked(view: View) {
		view.findNavController().navigateUp()
	}

	fun onEditClicked(view: View) {
		val validator = onNoteEditValidation(_id.value, title.value, text.value, color.value)

		launchDataLoad(validator) {
			repository.updateNote(_id.value!!, title.value!!, text.value!!, color.value!!)

			postMessage(R.string.note_edit_notification)
			view.findNavController().navigateUp()
		}
	}

	fun onDeleteClicked(view: View) {
		val validator = onNoteDeleteValidation(_id.value)

		launchDataLoad(validator) {
			repository.deleteNote(_id.value!!)

			postMessage(R.string.note_delete_notification)
			view.findNavController().navigateUp()
		}
	}
}