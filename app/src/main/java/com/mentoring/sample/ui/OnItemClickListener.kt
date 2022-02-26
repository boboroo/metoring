package com.mentoring.sample.ui

import com.mentoring.sample.data.models.UIData

fun interface OnItemClickListener {
    operator fun invoke(uiData: UIData)
}