package com.stefanusj.notesme.ui.note.add

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.stefanusj.notesme.R
import com.stefanusj.notesme.helper.Validator.onNoteAddValidation
import com.stefanusj.notesme.ui.BaseViewModel

class NoteAddViewModel(application: Application): BaseViewModel(application) {

	val title = MutableLiveData("")
	val text = MutableLiveData("")

	private val _color = MutableLiveData(0)
	val color: LiveData<Int> = _color

	fun onColorSet(color: Int) {
		_color.value = color
	}

	fun onBackClicked(view: View) {
		view.findNavController().navigateUp()
	}

	fun onSaveClicked(view: View) {
		val validator = onNoteAddValidation(title.value, text.value, color.value)

		launchDataLoad(validator) {
			repository.saveNote(title.value!!, text.value!!, color.value!!)

			postMessage(R.string.note_save_notification)
			view.findNavController().navigateUp()
		}
	}

}