package com.stefanusj.notesme

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.stefanusj.notesme.helper.gone
import com.stefanusj.notesme.helper.show

@BindingAdapter("isVisible")
fun isVisible(view: View, boolean: Boolean) {
	if (boolean) view.show() else view.gone()
}

@BindingAdapter("avatar")
fun avatar(view: ImageView, url: Uri?) {
	view.load(url)
}