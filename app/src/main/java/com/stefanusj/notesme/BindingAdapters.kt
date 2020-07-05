package com.stefanusj.notesme

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun isVisible(view: View, boolean: Boolean) {
	if (boolean) view.show() else view.gone()
}