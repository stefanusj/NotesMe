package com.stefanusj.notesme.repository

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.stefanusj.notesme.NotesMe
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent

class AppRepository(application: Application): KoinComponent {

	private val app = application as NotesMe

	private val auth: FirebaseAuth by lazy { Firebase.auth }
	private val firestore: FirebaseFirestore by lazy { Firebase.firestore }
	private val storage: FirebaseStorage by lazy { Firebase.storage }

	fun user() = auth.currentUser

	suspend fun signIn(account: GoogleSignInAccount): AuthResult = withContext(IO) {
		val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
		auth.signInWithCredential(credentials).await()
	}

	suspend fun signOut() = withContext(IO) {
		auth.signOut()
	}

	suspend fun deleteAccount() = withContext(IO) {
		if (auth.currentUser == null) return@withContext
		auth.currentUser!!.delete().await()
	}

}