package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.model.PullRequestsModel
import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import com.cvaccari.features.repositories.model.OwnerModel

object PullRequestsHelper {

    val successfulPullRequestRequest = listOf(
        PullRequestsModel("", "2017-05-24T08:39:20Z", "", OwnerModel("", ""), "", 0),
        PullRequestsModel("", "2017-05-24T08:39:20Z", "", OwnerModel("", ""), "", 0),
        PullRequestsModel("", "2017-05-24T08:39:20Z", "", OwnerModel("", ""), "", 0)
    )

    val request = PullRequestsRequestModel(OwnerModel("",""), "")

    val currentPage = 1

    val error = Exception()
}