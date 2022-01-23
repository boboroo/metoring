package com.mentoring.sample.ui.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mentoring.sample.databinding.ViewDummyContentBinding
import com.mentoring.sample.databinding.ViewDummyImageContentBinding
import com.mentoring.sample.ui.model.UIModel

sealed class MainUiViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {


    //이미지
    data class ImageViewHolder(private val binding: ViewDummyImageContentBinding) : MainUiViewHolder(binding) {
        fun bind(model: UIModel.ImageUIModel) {
            binding.model = model.data
        }
    }

    //상품 타입
    data class ProductViewHolder(private val binding: ViewDummyContentBinding) : MainUiViewHolder(binding) {
        fun bind(model: UIModel.DummyUIModel) {
            binding.model = model.data
        }
    }
}