package com.mentoring.sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mentoring.sample.data.models.UIData
import com.mentoring.sample.data.models.ViewType
import com.mentoring.sample.databinding.ViewImageBinding
import com.mentoring.sample.databinding.ViewProductListItemBinding
import com.shiny.shopping.data.models.ListUIData
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainRecyclerAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currentList = mutableListOf<ListUIData>()


    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return when (currentList[position].viewtype) {
            ViewType.PRODUCT -> ViewHolder(ViewProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            ViewType.IMAGE -> ViewHolder(ViewImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Non-existent view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.setData(currentList[position].data)
    }


    override fun getItemCount(): Int {
        return currentList.size
    }


    override fun getItemId(position: Int): Long {
        return (currentList[position].data.id?.toLong())?:Long.MIN_VALUE
    }


    fun setItems(items: List<ListUIData>) {
        currentList.clear()
        currentList.addAll(items)
        notifyDataSetChanged()
    }


    private class ViewHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {

        fun setData(model: UIData) {
            when(binding) {
                is ViewProductListItemBinding -> binding.model = model
                is ViewImageBinding -> binding.model = model
            }
        }

    }

}