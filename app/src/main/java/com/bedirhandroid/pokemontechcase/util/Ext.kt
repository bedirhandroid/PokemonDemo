package com.bedirhandroid.pokemontechcase.util

import android.view.View
import android.widget.ImageView
import com.bedirhandroid.pokemontechcase.R
import com.bumptech.glide.Glide


//infix funcs for views visibility
infix fun View.visibleIf(bool: Boolean) =
    if (bool) visible() else gone()

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

//ImageView ext for load image
fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_photo_error)
        .into(this)
}