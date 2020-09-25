package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.model.PullRequestsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullRequestsApi {

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repository") repository: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage : Int = ITEMS_PER_PAGE
    ): Single<List<PullRequestsModel>>

    companion object {
        private const val ITEMS_PER_PAGE = 20
    }
}