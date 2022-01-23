package com.mentoring.sample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mentoring.sample.R
import com.mentoring.sample.databinding.ViewDummyContentBinding
import com.mentoring.sample.ui.base.AbstractRecyclerAdapter
import com.mentoring.sample.ui.model.UIModel
import com.mentoring.sample.ui.viewholder.MainUiViewHolder
import com.mentoring.sample.util.dummy.DummyContents
import com.mentoring.sample.util.ex.bindView
import com.orhanobut.logger.Logger
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainRecyclerAdapter @Inject constructor() : AbstractRecyclerAdapter<UIModel, MainUiViewHolder>() {

    companion object {
        const val IMAGE_TYPE = 0
        const val PRODUCT_TYPE = 1
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainUiViewHolder {
//        //return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_dummy_content, parent, false))
//        return MainViewHolder(bindView(parent, R.layout.view_dummy_content))
//    }
//
//    override fun onBindViewHolder(holder: MainUiViewHolder, position: Int) {
//        holder.setData(currentList[position])
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainUiViewHolder {
        return when(viewType) {
            IMAGE_TYPE -> MainUiViewHolder.ImageViewHolder(bindView(parent, R.layout.view_dummy_image_content))
            PRODUCT_TYPE -> MainUiViewHolder.ProductViewHolder(bindView(parent, R.layout.view_dummy_content))
            else -> throw IllegalArgumentException("onCreateViewHolder viewType error")
        }
    }

    override fun onBindViewHolder(holder: MainUiViewHolder, position: Int) {
        when(holder) {
            is MainUiViewHolder.ImageViewHolder -> {
                holder.bind(currentList[position] as UIModel.ImageUIModel)
            }
            is MainUiViewHolder.ProductViewHolder -> {
                holder.bind(currentList[position] as UIModel.DummyUIModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is UIModel.ImageUIModel -> IMAGE_TYPE
            is UIModel.DummyUIModel -> PRODUCT_TYPE
        }
    }

    fun setItems(items: List<UIModel>) {
        currentList.clear()
        currentList.addAll(items)
        notifyDataSetChanged()
    }

//    //todo inner, binding, not binding
//    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//        private val binding: ViewDummyContentBinding by lazy {
//            requireNotNull(DataBindingUtil.bind(itemView))
//        }
//
//        fun setData(dummyContents: DummyContents) {
//            binding.model = dummyContents
//        }
//    }

    class MainViewHolder(private val binding: ViewDummyContentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(dummyContents: DummyContents) {
            binding.model = dummyContents
        }
    }


}