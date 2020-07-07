package com.stefanusj.notesme.repository

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.stefanusj.notesme.NotesMe
import com.stefanusj.notesme.model.Note
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import java.util.*

class AppRepository(application: Application): KoinComponent {

	private val app = application as NotesMe

	private val auth: FirebaseAuth by lazy { Firebase.auth }
	private val firestore: FirebaseFirestore by lazy { Firebase.firestore }
	private val storage: FirebaseStorage by lazy { Firebase.storage }

	private val noteDatabase = firestore.collection("notes")

	fun user() = auth.currentUser
	fun time() = Date().time

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

	suspend fun getNote(id: String): Note = withContext(IO) {
		val querySnapshot = noteDatabase.document(id).get().await()
		querySnapshot.toObject<Note>()!!
	}

	suspend fun getNotes(): List<Note> = withContext(IO) {
		val querySnapshot = noteDatabase
			.whereEqualTo("userId", auth.currentUser!!.uid)
			.orderBy("lastModified", DESCENDING)
			.get().await()

		val notes = mutableListOf(Note())
		withContext(Default) preparing@{
			querySnapshot.documents.forEach { document ->
				val note = document.toObject<Note>()!!
				note.id = document.id
				notes.add(note)
			}
		}
		notes
	}

	suspend fun saveNote(
		title: String,
		text: String,
		color: Int
	): DocumentReference = withContext(IO) {
		val note = Note(
			userId = auth.currentUser!!.uid,
			color = color,
			title = title,
			text = text,
			lastModified = time()
		)
		noteDatabase.add(note).await()
	}

	suspend fun updateNote(
		id: String,
		title: String,
		text: String,
		color: Int
	): Void? = withContext(IO) {
		noteDatabase.document(id).update(
			mapOf(
				"color" to color,
				"title" to title,
				"text" to text,
				"lastModified" to time()
			)
		).await()
	}

	suspend fun deleteNote(id: String): Void? = withContext(IO) {
		noteDatabase.document(id).delete().await()
	}
}