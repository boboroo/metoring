package com.mentoring.sample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mentoring.sample.R
import com.mentoring.sample.databinding.ViewDummyContentBinding
import com.mentoring.sample.util.dummy.DummyContents
import com.orhanobut.logger.Logger
import javax.inject.Inject

class MainRecyclerAdapter() : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    private val currentList = mutableListOf<DummyContents>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_dummy_content, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setData(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    fun setItems(items: List<DummyContents>) {
        currentList.clear()
        currentList.addAll(items)
        notifyDataSetChanged()
    }

    //todo inner, binding, not binding
    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: ViewDummyContentBinding by lazy {
            requireNotNull(DataBindingUtil.bind(itemView))
        }

        fun setData(dummyContents: DummyContents) {
            binding.model = dummyContents
        }
    }

}