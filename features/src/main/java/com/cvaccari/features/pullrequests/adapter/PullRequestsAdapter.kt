package com.cvaccari.features.pullrequests.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.features.R
import com.cvaccari.features.commons.listeners.RecyclerViewClickListener
import com.cvaccari.features.pullrequests.model.PullRequestsModel
import kotlinx.android.synthetic.main.item_pull_request.view.*

class PullRequestsAdapter(
    val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<PullRequestsAdapter.ViewHolder>() {

    var items: MutableList<PullRequestsModel> = mutableListOf()
        set(value) {
            items.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_pull_request,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class ViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(
            item: PullRequestsModel,
            listener: RecyclerViewClickListener
        ) {
            view.container_pull_request.setOnClickListener {
                listener.onClick(Uri.parse(item.repoURL()))
            }
            view.textview_title.text = item.title
        }
    }
}