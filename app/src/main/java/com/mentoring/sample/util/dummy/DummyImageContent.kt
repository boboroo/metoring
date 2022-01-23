package com.mentoring.sample.util.dummy

data class DummyImageContents(
    val id: String,
    val imageUrl: String,
    val onClick: ((id: String) -> Unit?)? = null
)