package com.mentoring.sample.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mentoring.sample.R

/**
 * 공통 바인딩만 적용
 */

@BindingAdapter("setGlide")
fun setImgUrl(imageView: ImageView, imageUrl: String?) {

    val loadImage = imageUrl.takeIf { ! it.isNullOrEmpty() }?.run {
        this
    } ?: R.drawable.ic_launcher_background

    Glide.with(imageView.rootView.context)
        .load(loadImage)
        .into(imageView)
}