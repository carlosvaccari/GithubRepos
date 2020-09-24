package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import com.cvaccari.features.pullrequests.model.PullRequestsModel
import io.reactivex.Single

interface PullRequestsContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showErrorMessage(message: String?)
        fun showErrorContainer()
        fun showPullRequests(items: List<PullRequestsModel>)
    }

    interface Presenter {
        fun getPullRequests(requestModel: PullRequestsRequestModel)
        fun dispose()
    }

    interface Repository {
        fun getPullRequests(requestModel: PullRequestsRequestModel): Single<List<PullRequestsModel>>
    }
}