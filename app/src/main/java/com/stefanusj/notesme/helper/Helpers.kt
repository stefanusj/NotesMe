package com.stefanusj.notesme.helper

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.firebase.auth.FirebaseUser

fun Context.toast(@StringRes stringRes: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, stringRes, length).show()
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

/**
 * Triggers a toast message with value [messageEvent].
 */
fun LifecycleOwner.setupMessage(
    context: Context,
    messageEvent: LiveData<Event<*>>,
    length: Int = Toast.LENGTH_SHORT
) {
    messageEvent.observeEvent(this) {
        if (it is Int) {
            context.toast(it, length)
        } else if (it is String) {
            context.toast(it, length)
        }
    }
}

fun <T> LiveData<T>.debounce(duration: Long = 1000L) = MediatorLiveData<T>().also { mld ->
	val source = this
	val handler = Handler(Looper.getMainLooper())

	val runnable = Runnable {
		mld.value = source.value
	}

	mld.addSource(source) {
		handler.removeCallbacks(runnable)
		handler.postDelayed(runnable, duration)
	}
}

typealias User = FirebaseUser?
