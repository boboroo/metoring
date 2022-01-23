package com.mentoring.sample.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentoring.sample.util.dummy.DummyContents

abstract class AbstractRecyclerAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var currentList = mutableListOf<T>()


    override fun getItemCount(): Int {
        return currentList.size
    }

}