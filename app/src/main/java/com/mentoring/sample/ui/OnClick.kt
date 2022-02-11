package com.mentoring.sample.ui

import com.mentoring.sample.data.models.UIData

fun interface OnClick {
    operator fun invoke(uiData: UIData)
}