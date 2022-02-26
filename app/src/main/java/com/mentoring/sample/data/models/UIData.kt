package com.mentoring.sample.data.models

import android.os.Parcelable
import com.mentoring.sample.ui.OnItemClickListener
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class UIData(
    val id: String,
    val image: String,
    val price: Int,
    val name: String,
    val detail: String,
    var onItemClickListener: @RawValue OnItemClickListener
): Parcelable
