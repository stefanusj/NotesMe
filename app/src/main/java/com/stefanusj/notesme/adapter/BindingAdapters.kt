package com.stefanusj.notesme.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.google.android.material.button.MaterialButton
import com.stefanusj.notesme.helper.gone
import com.stefanusj.notesme.helper.show
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("avatar")
fun avatar(view: ImageView, url: Uri?) {
	view.load(url)
}

@BindingAdapter("color")
fun color(view: View, color: Int) {
	if (view is CardView) {
		view.setCardBackgroundColor(color)
	} else {
		view.setBackgroundColor(color)
	}
}

@BindingAdapter("date")
fun date(view: TextView, timestamp: Long?) {
	try {
		val sdf = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
		val netDate = Date(timestamp!!)
		view.text = sdf.format(netDate)
	} catch (e: Exception) {
		view.text = timestamp.toString()
	}
}

@BindingAdapter("icon")
fun icon(view: MaterialButton, icon: Int) {
	view.setIconResource(icon)
}

@BindingAdapter("isVisible")
fun isVisible(view: View, boolean: Boolean) {
	if (boolean) view.show() else view.gone()
}