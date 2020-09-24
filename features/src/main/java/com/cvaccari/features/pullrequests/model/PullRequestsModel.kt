package com.cvaccari.features.pullrequests.model

import com.cvaccari.core.BuildConfig
import com.cvaccari.features.repositories.model.OwnerModel
import com.google.gson.annotations.SerializedName

class PullRequestsModel(
    @SerializedName("body") val body: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("title") val title: String,
    @SerializedName("user") val user: OwnerModel,
    @SerializedName("url") val url: String,
    @SerializedName("number") val number: Int,
    var organizationName: String = "" ,
    var repositoryName: String = ""
) {

    fun repoURL() = "${BuildConfig.GITHUB_BASE_URL}$organizationName/$repositoryName/pull/$number"

}