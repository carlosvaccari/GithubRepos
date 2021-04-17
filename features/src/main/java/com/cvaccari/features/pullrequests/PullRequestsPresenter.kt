package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import io.reactivex.disposables.Disposable

class PullRequestsPresenter(
    val view: PullRequestsContract.View,
    val repository: PullRequestsContract.Repository
) : PullRequestsContract.Presenter {

    var disposable: Disposable? = null

    override fun getPullRequests(requestModel: PullRequestsRequestModel, isLoadingMore: Boolean) {
        disposable = repository.getPullRequests(requestModel)
            .doOnSubscribe { showLoading(isLoadingMore) }
            .doFinally { hideLoading(isLoadingMore) }
            .subscribe({
                view.showPullRequests(it)
            }, {
                view.showErrorContainer()
            })
    }

    private fun hideLoading(isLoadingMore: Boolean) = if (isLoadingMore) view.hideProgressBar() else view.hideLoading()

    private fun showLoading(isLoadingMore: Boolean) = if(isLoadingMore) view.showProgressBar() else view.showLoading()

    override fun dispose() {
        disposable?.dispose()
    }
}