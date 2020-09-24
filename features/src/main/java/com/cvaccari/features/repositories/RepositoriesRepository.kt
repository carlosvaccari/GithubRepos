package com.cvaccari.features.repositories

import com.cvaccari.features.commons.extensions.callOnMainThread
import com.cvaccari.features.repositories.model.RepositoriesModel
import io.reactivex.Single

class RepositoriesRepository(val api: RepositoriesApi) : RepositoriesContract.Repository {

    override fun getRepositories(): Single<RepositoriesModel> = api.getRepositories()
        .callOnMainThread()
}