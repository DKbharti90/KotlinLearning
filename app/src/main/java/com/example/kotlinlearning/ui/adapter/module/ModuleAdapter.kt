package com.example.kotlinlearning.ui.adapter.module

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.kotlinlearning.data.entity.Module

class ModuleAdapter( private val itemClickListener: (View, Module) -> Unit) : PagingDataAdapter<ModuleListItem,ModuleViewHolder>(diffCallback) {



    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        return ModuleViewHolder(parent,itemClickListener)
    }
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ModuleListItem>() {
            override fun areItemsTheSame(
                oldItem: ModuleListItem,
                newItem: ModuleListItem
            ): Boolean {
                return if (oldItem is ModuleListItem.Item && newItem is ModuleListItem.Item) {
                    oldItem.module.id == newItem.module.id
                } else if (oldItem is ModuleListItem.Separator && newItem is ModuleListItem.Separator) {
                    oldItem.name == newItem.name
                } else {
                    oldItem == newItem
                }
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(
                oldItem: ModuleListItem,
                newItem: ModuleListItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}