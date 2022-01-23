package com.mentoring.sample.util.ex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


fun <T : ViewDataBinding> bindView(parent: ViewGroup, layoutRes: Int): T {
    return DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        layoutRes,
        parent,
        false
    )
}