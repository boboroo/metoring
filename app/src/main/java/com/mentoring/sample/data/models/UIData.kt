package com.mentoring.sample.data.models

data class UIData(
    val id: String,
    val image: String,
    val price: Int,
    val name: String,
    val detail: String,
    var onClick: (id: String) -> Unit
)
