package com.cvaccari.features.repositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cvaccari.features.R
import com.cvaccari.features.commons.listeners.RecyclerViewClickListener
import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
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
        holder.bind(items[position], listener)
    }

    class ViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(
            item: RepositoryModel,
            listener: RecyclerViewClickListener
        ) {
            view.container_repository.setOnClickListener {
                listener.onClick(
                    PullRequestsRequestModel(
                        item.owner.login,
                        item.name
                    )
                )
            }
            var requestOptions = RequestOptions()
            requestOptions = requestOptions
                .error(R.drawable.background_placeholder_oval)
                .placeholder(R.drawable.background_placeholder_oval)

            Glide.with(view.context)
                .load(item.owner.image)
                .apply(requestOptions)
                .into(view.imageview_user)

            view.textview_stars.text = item.stargazersCount.toString()
            view.textview_fork.text = item.forksCount.toString()
            view.textview_repository_name.text = item.name
            view.textview_repository_description.text = item.description
            view.textview_name.text = item.owner.login
            view.textview_user_name.text = "Username"

        }
    }
}