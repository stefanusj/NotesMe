package com.stefanusj.notesme

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}