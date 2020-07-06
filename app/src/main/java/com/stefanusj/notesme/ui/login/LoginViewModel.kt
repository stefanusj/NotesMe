package com.stefanusj.notesme.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.stefanusj.notesme.state.LoginState
import com.stefanusj.notesme.state.LoginState.AUTHENTICATED
import com.stefanusj.notesme.state.LoginState.UNAUTHENTICATED
import com.stefanusj.notesme.ui.BaseViewModel

class LoginViewModel(application: Application): BaseViewModel(application) {

	val loginState = MutableLiveData<LoginState>()

	init {
		loginState.value = if (repository.user() == null) UNAUTHENTICATED else AUTHENTICATED
	}

	fun setGoogleAccount(account: GoogleSignInAccount) {
		launchDataLoad {
			repository.signIn(account)
			postMessage("Successfully Signed In!")

			loginState.postValue(AUTHENTICATED)
		}
	}
}