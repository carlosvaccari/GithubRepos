package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.model.PullRequestsModel
import com.cvaccari.features.pullrequests.model.PullRequestsRequestModel
import com.cvaccari.features.repositories.model.OwnerModel

object PullRequestsHelper {

    val successfulPullRequestRequest = listOf(
        PullRequestsModel("", "", "", OwnerModel("", ""), "", 0),
        PullRequestsModel("", "", "", OwnerModel("", ""), "", 0),
        PullRequestsModel("", "", "", OwnerModel("", ""), "", 0)
    )

    val request = PullRequestsRequestModel("", "")

    val error = Exception()
}