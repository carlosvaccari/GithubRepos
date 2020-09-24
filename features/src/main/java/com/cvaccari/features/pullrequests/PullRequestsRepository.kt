package com.cvaccari.features.pullrequests

import com.cvaccari.features.commons.extensions.callOnMainThread
import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import com.cvaccari.features.pullrequests.model.PullRequestsModel
import io.reactivex.Single

class PullRequestsRepository(val api: PullRequestsApi) : PullRequestsContract.Repository {

    override fun getPullRequests(requestsModel: PullRequestsRequestModel): Single<List<PullRequestsModel>> =
        api.getPullRequests(requestsModel.owner, requestsModel.repository).callOnMainThread()
}