package com.cvaccari.features.repositories

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cvaccari.features.R
import com.cvaccari.features.base.BaseFragment
import com.cvaccari.features.commons.extensions.goTo
import com.cvaccari.features.commons.extensions.gone
import com.cvaccari.features.commons.extensions.showFeedback
import com.cvaccari.features.commons.extensions.visible
import com.cvaccari.features.commons.listeners.RecyclerViewClickListener
import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import com.cvaccari.features.repositories.adapter.RepositoriesAdapter
import com.cvaccari.features.repositories.model.RepositoriesModel
import kotlinx.android.synthetic.main.error_container.*
import kotlinx.android.synthetic.main.fragment_repositories_list.*
import org.kodein.di.Kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class RepositoriesFragment : BaseFragment(), RepositoriesContract.View, RecyclerViewClickListener {

    override val kodein: Kodein by kodein()

    private val presenter: RepositoriesContract.Presenter by instance { this }

    private var adapter = RepositoriesAdapter(this)

    override fun getLayoutResource() = R.layout.fragment_repositories_list

    override fun initComponents(view: View) {
        init()
    }

    private fun init() {
        presenter.getRepositories()
    }

    override fun showLoading() {
        container_loading.visible()
        container_error.gone()
    }

    override fun hideLoading() {
        container_loading.gone()
    }

    override fun showErrorMessage(message: String?) {
        view.showFeedback(message)
    }

    override fun showErrorContainer() {
        container_error.visible()
        button_try_again.setOnClickListener { init() }
    }

    override fun showRepositories(items: RepositoriesModel) {
        recyclerview_repositories.layoutManager = LinearLayoutManager(context)
        recyclerview_repositories.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerview_repositories.adapter = adapter.apply {
            this.items = items.items.toMutableList()
        }
        recyclerview_repositories.visible()
    }

    override fun onClick(item: Any) {
        goTo(
            RepositoriesFragmentDirections.actionFragmentRepositoriesListToFragmentPullRequestsList(
                item as PullRequestsRequestModel
            )
        )
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}