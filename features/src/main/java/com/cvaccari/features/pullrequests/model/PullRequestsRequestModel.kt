package com.cvaccari.features.pullrequests.model

import com.cvaccari.features.repositories.model.OwnerModel
import java.io.Serializable

data class PullRequestsRequestModel(
    val owner: OwnerModel,
    val repository: String
): Serializable