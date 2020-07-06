package com.stefanusj.notesme.ui.login

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.stefanusj.notesme.R
import com.stefanusj.notesme.databinding.LoginFragmentBinding
import com.stefanusj.notesme.state.LoginState.AUTHENTICATED
import com.stefanusj.notesme.ui.BaseFragment
import org.koin.android.ext.android.inject

class LoginFragment: BaseFragment<LoginFragmentBinding>() {

	override val layoutId: Int = R.layout.login_fragment
	override val viewModel: LoginViewModel by viewModels()

	override val shouldAuthentication: Boolean = false

	private val googleClient: GoogleSignInClient by inject()

	override fun init() = with(binding) {
		mbSignInGoogle.setOnClickListener {
			googleClient.signInIntent.also {
				startActivityForResult(it, RC_SIGN_IN)
			}
		}
	}

	override fun subscribeUi(): Unit = with(viewModel) {
		super.subscribeUi()
		loginState.observe(viewLifecycleOwner) { state ->
			when (state) {
				AUTHENTICATED -> LoginFragmentDirections.toNote()
					.also(findNavController()::navigate)
				else          -> {
				}
			}
		}
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == RC_SIGN_IN) {
			val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
			account?.let { viewModel.setGoogleAccount(it) }
		}
	}

	companion object {
		private const val RC_SIGN_IN = 0
	}
}