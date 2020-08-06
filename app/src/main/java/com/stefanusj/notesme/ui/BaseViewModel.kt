package com.stefanusj.notesme.ui

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.stefanusj.notesme.NotesMe
import com.stefanusj.notesme.R
import com.stefanusj.notesme.helper.Event
import com.stefanusj.notesme.helper.ValidationException
import com.stefanusj.notesme.repository.AppRepository
import com.stefanusj.notesme.repository.model.Validation
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException

/**
 * Base class for view model with data binding
 */
abstract class BaseViewModel(application: Application): AndroidViewModel(application),
	LifecycleObserver,
	KoinComponent {

	/**
	 * Application class
	 */
	protected val app = application as NotesMe

	/**
	 * Main Repository Application, Single Source of Truth
	 */
	protected val repository: AppRepository by inject()

	/**
	 *  Return [_authenticated] true if user is authenticated
	 */
	private val _authenticated = MutableLiveData<Boolean>()
	val authenticated: LiveData<Boolean> = _authenticated

	/**
	 * Show a loading spinner if [isLoading] is true
	 */
	private val _isLoading = MutableLiveData<Boolean>(false)
	val isLoading: LiveData<Boolean> = _isLoading

	/**
	 * Variable container for event notification
	 */
	private val _messageText = MutableLiveData<Event<*>>()
	val messageText: LiveData<Event<*>> = _messageText

	/**
	 * Post message to Observer UI using LiveData object
	 * from given [resId]
	 */
	fun postMessage(@StringRes resId: Int) = _messageText.postValue(Event(resId))

	/**
	 * Post message to Observer UI using LiveData object
	 * from given [message]
	 */
	fun postMessage(message: String) = _messageText.postValue(Event(message))

	/**
	 * Launch a data load using coroutine [viewModelScope]
	 *
	 * Wrapped inside try catch to make sure apps aren't crash
	 * while calling data from API
	 */
	protected fun launchDataLoad(validator: Validation? = null, block: suspend () -> Unit): Job {
		return viewModelScope.launch {
			try {
				validator?.let { if (!it.isValid) throw ValidationException(validator.problem) }

				_isLoading.value = true
				block()
			} catch (e: ValidationException) {
				e.problem?.let { postMessage(it) }
			} catch (e: IOException) {
				e.printStackTrace()
				postMessage(R.string.network_problem)
			} catch (e: Exception) {
				e.printStackTrace()
				postMessage(e.message.toString())
			} finally {
				_isLoading.value = false
			}
		}
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
	fun checkIfAuthenticated() {
		repository.user().also { user ->
			_authenticated.value = user != null
		}
	}

}
