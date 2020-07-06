package com.stefanusj.notesme.ui.note

import android.app.Application
import android.net.Uri
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.findNavController
import com.stefanusj.notesme.ui.BaseViewModel

class NoteViewModel(application: Application): BaseViewModel(application) {

	private val _photoUrl = MutableLiveData<Uri>()
	val photoUrl: LiveData<Uri> = _photoUrl

	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
	fun loadPhotoUrl() {
		_photoUrl.value = repository.user()?.photoUrl
	}

	fun onAvatarClicked(view: View) {
		NoteFragmentDirections.toProfile()
			.also(view.findNavController()::navigate)
	}
}