package com.mentoring.sample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mentoring.sample.data.db.entities.RecentlyViewedProduct
import com.mentoring.sample.data.models.UIData
import com.mentoring.sample.data.models.ViewType
import com.mentoring.sample.databinding.ViewImageBinding
import com.mentoring.sample.databinding.ViewProductListItemBinding
import com.mentoring.sample.databinding.ViewRecentlyProductListItemBinding
import com.shiny.shopping.data.models.ListUIData
import java.lang.IllegalArgumentException
import javax.inject.Inject

class RecentlyProductsRecyclerAdapter @Inject constructor(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currentList = mutableListOf<RecentlyViewedProduct>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ViewRecentlyProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.setData(currentList[position])
    }


    override fun getItemCount(): Int {
        return currentList.size
    }


    override fun getItemId(position: Int): Long {
        return (currentList[position].id?.toLong())?:Long.MIN_VALUE
    }


    fun setItems(items: List<RecentlyViewedProduct>) {
        currentList.clear()
        currentList.addAll(items)
        notifyDataSetChanged()
    }


    private class ViewHolder(private val binding: ViewRecentlyProductListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun setData(model: RecentlyViewedProduct) {
            binding.model = model
        }

    }

}