package com.cvaccari.features.pullrequests

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cvaccari.customviews.recyclerview.CustomRecyclerView
import com.cvaccari.features.R
import com.cvaccari.features.base.BindableBaseFragment
import com.cvaccari.features.commons.extensions.addDecorator
import com.cvaccari.features.commons.extensions.gone
import com.cvaccari.features.commons.extensions.showFeedback
import com.cvaccari.features.commons.extensions.visible
import com.cvaccari.features.commons.listeners.RecyclerViewClickListener
import com.cvaccari.features.databinding.FragmentPullRequestsListBinding
import com.cvaccari.features.pullrequests.adapter.PullRequestsAdapter
import com.cvaccari.features.pullrequests.model.PullRequestsModel
import org.kodein.di.Kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class PullRequestsFragment : BindableBaseFragment<FragmentPullRequestsListBinding>(),
    PullRequestsContract.View, RecyclerViewClickListener,
    CustomRecyclerView.LoadMoreListener {

    override val kodein: Kodein by kodein()

    private val presenter: PullRequestsContract.Presenter by instance { this }

    private val args by navArgs<PullRequestsFragmentArgs>()

    override fun initializeViewBinding(view: View) = FragmentPullRequestsListBinding.bind(view)

    override fun getLayoutResource() = R.layout.fragment_pull_requests_list

    private var adapter = PullRequestsAdapter(this)

    override fun initComponents(binding: FragmentPullRequestsListBinding) {
        initRecyclerview()
        init()
    }

    private fun initRecyclerview() {
        binding.recyclerviewPullRequests.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewPullRequests.addDecorator()
        binding.recyclerviewPullRequests.setOnLoadMoreListener(this@PullRequestsFragment)
        binding.recyclerviewPullRequests.adapter = adapter
        binding.recyclerviewPullRequests.visible()
    }

    override fun showLoading() {
        binding.containerLoading.containerLoading.visible()
        binding.containerError.contentError.gone()
    }

    override fun hideLoading() {
        binding.containerLoading.containerLoading.gone()
    }

    override fun showProgressBar() = binding.progressbar.visible()

    override fun hideProgressBar() = binding.progressbar.gone()

    override fun showErrorMessage(message: String?) = view.showFeedback(message)

    override fun showErrorContainer() = with(binding.containerError) {
        contentError.visible()
        buttonTryAgain.setOnClickListener { init() }
    }

    override fun showPullRequests(items: List<PullRequestsModel>) {
        binding.recyclerviewPullRequests.isLoading = false
        adapter.apply { this.items = items.toMutableList() }
        binding.recyclerviewPullRequests.startAnim()
    }

    private fun init() = presenter.getPullRequests(args.requestModel)

    override fun onClick(item: Any) = startActivity(Intent(Intent.ACTION_VIEW, item as Uri))

    override fun loadMore() {
        binding.recyclerviewPullRequests.isLoading = true
        presenter.getPullRequests(args.requestModel, true)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}