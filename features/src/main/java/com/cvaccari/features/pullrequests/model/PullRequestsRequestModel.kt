package com.cvaccari.features.pullrequests.model

import java.io.Serializable

data class PullRequestsRequestModel(
    val owner: String,
    val repository: String
): Serializable