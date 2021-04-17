package com.cvaccari.features.repositories

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.cvaccari.customviews.recyclerview.CustomRecyclerView
import com.cvaccari.features.R
import com.cvaccari.features.base.BindableBaseFragment
import com.cvaccari.features.commons.extensions.*
import com.cvaccari.features.commons.listeners.RecyclerViewClickListener
import com.cvaccari.features.databinding.FragmentRepositoriesListBinding
import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import com.cvaccari.features.repositories.adapter.RepositoriesAdapter
import com.cvaccari.features.repositories.model.RepositoriesModel
import org.kodein.di.Kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class RepositoriesFragment : BindableBaseFragment<FragmentRepositoriesListBinding>(),
    RepositoriesContract.View, RecyclerViewClickListener,
    CustomRecyclerView.LoadMoreListener {

    override val kodein: Kodein by kodein()

    private val presenter: RepositoriesContract.Presenter by instance { this }

    private var adapter = RepositoriesAdapter(this)

    override fun getLayoutResource() = R.layout.fragment_repositories_list

    override fun initializeViewBinding(view: View) = FragmentRepositoriesListBinding.bind(view)

    override fun initComponents(binding: FragmentRepositoriesListBinding) {
        initRecyclerView()
        init()
    }

    private fun initRecyclerView() {
        binding.recyclerviewRepositories.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewRepositories.addDecorator()
        binding.recyclerviewRepositories.setOnLoadMoreListener(this)
        binding.recyclerviewRepositories.adapter = adapter
        binding.recyclerviewRepositories.visible()
    }

    private fun init() {
        presenter.getRepositories()
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

    override fun showRepositories(items: RepositoriesModel) {
        binding.recyclerviewRepositories.isLoading = false
        adapter.apply { this.items = items.items.toMutableList() }
        binding.recyclerviewRepositories.startAnim()
    }

    override fun loadMore() {
        binding.recyclerviewRepositories.isLoading = true
        presenter.getRepositories(true)
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