package com.cvaccari.features.repositories

import com.cvaccari.features.commons.PageController
import com.cvaccari.features.commons.extensions.callOnMainThread
import com.cvaccari.features.repositories.model.RepositoriesModel
import io.reactivex.Single

class RepositoriesRepository(val api: RepositoriesApi) : PageController(),
    RepositoriesContract.Repository {

    override fun getRepositories(): Single<RepositoriesModel> =
        registerObservable(api.getRepositories(page = currentPage))
            .callOnMainThread()
}

