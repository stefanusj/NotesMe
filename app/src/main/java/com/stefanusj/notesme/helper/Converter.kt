package com.stefanusj.notesme.helper

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import kotlin.math.ln
import kotlin.math.pow

fun @receiver:DrawableRes Int.toDrawable(context: Context) =
    ContextCompat.getDrawable(context, this)

fun @receiver:ColorRes Int.toColor(context: Context) =
    ContextCompat.getColor(context, this)

fun @receiver:ColorRes Int.toColorDrawable(context: Context) =
    ColorDrawable(toColor(context))

fun Long.toHumanReadableByte(si: Boolean = true): String {

    val unit = if (si) 1000 else 1024

    if (this < unit) return "$this B"

    val exp = (ln(this.toDouble()) / ln(unit.toDouble())).toInt()
    val pre = (if (si) "kMGTPE" else "KMGTPE")[exp - 1] + if (si) "" else "i"
    return String.format("%.1f %sB", this / unit.toDouble().pow(exp.toDouble()), pre)
}