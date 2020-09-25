package com.cvaccari.features.pullrequests.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.features.R
import com.cvaccari.features.commons.extensions.setSafeText
import com.cvaccari.features.commons.extensions.toFormattedDate
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
            view.textview_pull_request_title.text = item.title
            view.textview_pull_request_description.setSafeText(
                HtmlCompat.fromHtml(
                    item.body,
                    FROM_HTML_MODE_LEGACY
                )
            )
            view.imageview_user.setImageFromUrl(item.ownerOrganization?.image)
            view.textview_name.text = item.ownerOrganization?.login
            view.textview_created_at.text = item.createdAt.toFormattedDate()
        }
    }
}