package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.model.PullRequestsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestsApi {

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repository") repository: String
    ): Single<List<PullRequestsModel>>
}