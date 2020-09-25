package com.cvaccari.features.repositories

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cvaccari.customviews.recyclerview.CustomRecyclerView
import com.cvaccari.features.R
import com.cvaccari.features.base.BaseFragment
import com.cvaccari.features.commons.extensions.*
import com.cvaccari.features.commons.listeners.RecyclerViewClickListener
import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import com.cvaccari.features.repositories.adapter.RepositoriesAdapter
import com.cvaccari.features.repositories.model.RepositoriesModel
import kotlinx.android.synthetic.main.error_container.*
import kotlinx.android.synthetic.main.fragment_repositories_list.*
import org.kodein.di.Kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class RepositoriesFragment : BaseFragment(), RepositoriesContract.View, RecyclerViewClickListener,
    CustomRecyclerView.LoadMoreListener {

    override val kodein: Kodein by kodein()

    private val presenter: RepositoriesContract.Presenter by instance { this }

    private var adapter = RepositoriesAdapter(this)

    override fun getLayoutResource() = R.layout.fragment_repositories_list

    override fun initComponents(view: View) {
        initRecyclerView()
        init()
    }

    private fun initRecyclerView() {
        recyclerview_repositories.layoutManager = LinearLayoutManager(context)
        recyclerview_repositories.addDecorator()
        recyclerview_repositories.setOnLoadMoreListener(this)
        recyclerview_repositories.adapter = adapter
        recyclerview_repositories.visible()
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
        recyclerview_repositories.isLoading = false
        adapter.apply { this.items = items.items.toMutableList() }
        recyclerview_repositories.startAnim()
    }

    override fun loadMore() {
        recyclerview_repositories.isLoading = true
        presenter.getRepositories()
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