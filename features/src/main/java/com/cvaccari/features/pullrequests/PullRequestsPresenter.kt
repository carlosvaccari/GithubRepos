package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import io.reactivex.disposables.Disposable

class PullRequestsPresenter(
    val view: PullRequestsContract.View,
    val repository: PullRequestsContract.Repository
) : PullRequestsContract.Presenter {

    var disposable: Disposable? = null

    override fun getPullRequests(requestModel: PullRequestsRequestModel) {
        disposable = repository.getPullRequests(requestModel)
            .doOnSubscribe { view.showLoading() }
            .doFinally { view.hideLoading() }
            .subscribe({
                view.showPullRequests(it)
            }, {
                view.showErrorContainer()
            })
    }

    override fun dispose() {
        disposable?.dispose()
    }
}