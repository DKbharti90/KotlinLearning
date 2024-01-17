package com.example.kotlinlearning.ui.adapter.module

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlearning.R
import com.example.kotlinlearning.data.entity.Module

class ModuleViewHolder(parent: ViewGroup, private val itemClickListener: (View, Module) -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.cheese_item, parent, false)
)  {
    var module: Module? = null
        private set
    private val nameView = itemView.findViewById<TextView>(R.id.name)

    init {

        itemView.setOnClickListener { view->
            this.module?.let { itemClickListener.invoke(view, it) }
        }
    }

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(item: ModuleListItem?) {
        if (item is ModuleListItem.Separator) {
            nameView.text = "${item.name} Cheeses"
            nameView.setTypeface(null, Typeface.BOLD)
        } else {
            nameView.text = item?.name
            nameView.setTypeface(null, Typeface.NORMAL)

        }
        module = (item as? ModuleListItem.Item)?.module
        nameView.text = item?.name

    }
}