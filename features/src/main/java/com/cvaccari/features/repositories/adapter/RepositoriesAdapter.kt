package com.cvaccari.features.repositories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.features.R
import com.cvaccari.features.commons.listeners.RecyclerViewClickListener
import com.cvaccari.features.repositories.model.RepositoryModel
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoriesAdapter(
    val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {

    var items: MutableList<RepositoryModel> = mutableListOf()
        set(value) {
            items.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_repository,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], holder.view.context, listener)
    }

    class ViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(
            item: RepositoryModel,
            context: Context,
            listener: RecyclerViewClickListener
        ) {
            view.textview_repository_name.text = item.name
        }
    }
}