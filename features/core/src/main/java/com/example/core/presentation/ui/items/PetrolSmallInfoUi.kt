package com.example.core.presentation.ui.items

import androidx.databinding.ViewDataBinding
import androidx.databinding.adapters.ViewBindingAdapter
import com.example.core.R
import com.example.core.databinding.ItemPetrolSmallInfoBinding
import com.xwray.groupie.databinding.BindableItem

class PetrolSmallInfoUi(

) : BindableItem<ViewDataBinding>() {

    override fun getLayout(): Int = R.layout.item_petrol_small_info

    override fun bind(viewBinding: ViewDataBinding, position: Int) {
        when (viewBinding) {
            is ItemPetrolSmallInfoBinding -> {
                viewBinding.data = this
            }
        }
    }

}