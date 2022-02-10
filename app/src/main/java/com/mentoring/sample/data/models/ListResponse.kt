package com.shiny.shopping.data.models

data class ListResponse(
    val result: String,
    val data: List<ListUIData>
)