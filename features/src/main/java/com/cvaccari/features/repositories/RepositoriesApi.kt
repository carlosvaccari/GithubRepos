package com.cvaccari.features.repositories

import com.cvaccari.features.repositories.model.RepositoriesModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoriesApi {

    @GET("search/repositories")
    fun getRepositories(
        @Query("q") language: String = DEFAULT_LANGUAGE,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage : Int = ITEMS_PER_PAGE
    ): Single<RepositoriesModel>

    companion object {
        private const val DEFAULT_LANGUAGE = "language:Kotlin"
        private const val ITEMS_PER_PAGE = 20
    }
}