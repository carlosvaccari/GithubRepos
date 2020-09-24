package com.cvaccari.features.pullrequests

import android.content.Intent
import android.net.Uri
import android.provider.Browser
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cvaccari.features.R
import com.cvaccari.features.base.BaseFragment
import com.cvaccari.features.commons.extensions.gone
import com.cvaccari.features.commons.extensions.showFeedback
import com.cvaccari.features.commons.extensions.visible
import com.cvaccari.features.commons.listeners.RecyclerViewClickListener
import com.cvaccari.features.pullrequests.adapter.PullRequestsAdapter
import com.cvaccari.features.pullrequests.model.PullRequestsModel
import kotlinx.android.synthetic.main.error_container.*
import kotlinx.android.synthetic.main.fragment_pull_requests_list.*
import kotlinx.android.synthetic.main.fragment_repositories_list.container_error
import kotlinx.android.synthetic.main.fragment_repositories_list.container_loading
import org.kodein.di.Kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class PullRequestsFragment : BaseFragment(), PullRequestsContract.View, RecyclerViewClickListener {

    override val kodein: Kodein by kodein()

    private val presenter: PullRequestsContract.Presenter by instance { this }

    override fun getLayoutResource() = R.layout.fragment_pull_requests_list

    private var adapter = PullRequestsAdapter(this)

    override fun initComponents(view: View) {
        init()
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

    override fun showPullRequests(items: List<PullRequestsModel>) {
        recyclerview_pull_requests.layoutManager = LinearLayoutManager(context)
        recyclerview_pull_requests.adapter = adapter.apply { this.items = items.toMutableList() }
        recyclerview_pull_requests.visible()
    }

    private fun init() {
        arguments?.apply {
            presenter.getPullRequests(PullRequestsFragmentArgs.fromBundle(this).requestModel)
        }
    }

    override fun onClick(item: Any) {
        startActivity(Intent(Intent.ACTION_VIEW, item as Uri).apply {
            putExtra(Browser.EXTRA_APPLICATION_ID, requireContext().packageName)
        })
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}