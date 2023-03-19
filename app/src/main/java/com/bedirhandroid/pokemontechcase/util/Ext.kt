package com.bedirhandroid.pokemontechcase.util

import android.view.View
import android.widget.ImageView
import com.bedirhandroid.pokemontechcase.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


infix fun View.visibleIf(bool: Boolean) =
    if (bool) visible() else gone()

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_photo_error)
        .into(this)
}