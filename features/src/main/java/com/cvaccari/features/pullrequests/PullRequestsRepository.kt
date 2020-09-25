package com.cvaccari.features.pullrequests

import com.cvaccari.features.commons.extensions.callOnMainThread
import com.cvaccari.features.pullrequests.model.PullRequestsModel
import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import io.reactivex.Single

class PullRequestsRepository(val api: PullRequestsApi) : PullRequestsContract.Repository {

    override fun getPullRequests(requestsModel: PullRequestsRequestModel): Single<List<PullRequestsModel>> =
        requestPullRequests(requestsModel).map {
            it.forEach {
                it.organizationName = requestsModel.owner; it.repositoryName =
                requestsModel.repository
            }
            it
        }.callOnMainThread()

    fun requestPullRequests(requestsModel: PullRequestsRequestModel) =
        api.getPullRequests(requestsModel.owner, requestsModel.repository)
}