package com.stefanusj.notesme.ui.profile

import android.app.Application
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.findNavController
import com.stefanusj.notesme.helper.User
import com.stefanusj.notesme.ui.BaseViewModel

class ProfileViewModel(application: Application): BaseViewModel(application) {

	private val _user = MutableLiveData<User>()
	val user: LiveData<User> = _user

	val darkModeTheme = repository.darkModeTheme.mode

	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
	fun setUser() {
		_user.value = repository.user()
	}

	fun onSwitchDarkModeThemeClicked(view: View) {
		repository.darkModeTheme.switchDarkModeTheme()
	}

	fun onBackClicked(view: View) {
		view.findNavController().navigateUp()
	}

	fun onSignOutClicked(view: View) {
		launchDataLoad {
			repository.signOut()
			ProfileFragmentDirections.toLogin()
				.also(view.findNavController()::navigate)
		}
	}

	fun onDeleteAccountClicked(view: View) {
		launchDataLoad {
			repository.deleteAccount()
			ProfileFragmentDirections.toLogin()
				.also(view.findNavController()::navigate)
		}
	}
}