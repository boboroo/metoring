package com.mentoring.sample.util.dummy

import androidx.annotation.DrawableRes

data class DummyContents(
    val id: String,
    @DrawableRes val iconRes: Int,
    val price: Int,
    val onClick: (id: String) -> Unit
)