package com.sehatq.test.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sehatq.test.databinding.ItemCategoryBinding
import com.sehatq.test.domain.models.Category
import com.sehatq.test.ui.base.BaseAdapter

class CategoryListAdapter(data: ArrayList<Category>,
                          var action: (Category, ItemCategoryBinding) -> Unit)
    : BaseAdapter<Category>(data) {

    private lateinit var binding: ItemCategoryBinding


    override fun createHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return GenericViewHolder(binding)
    }

    override fun bindingViewHolder(holder: GenericViewHolder, position: Int) {
        if (holder.viewDataBinding is ItemCategoryBinding) {
            (holder.viewDataBinding as ItemCategoryBinding).itemViewModel =
                    CategoryListItemViewModel(getItem(position), action, binding)
        }
    }


}