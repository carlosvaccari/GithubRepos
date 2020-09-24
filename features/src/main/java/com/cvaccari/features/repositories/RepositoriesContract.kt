package com.cvaccari.features.repositories

import com.cvaccari.features.repositories.model.RepositoriesModel
import io.reactivex.Observable
import io.reactivex.Single

interface RepositoriesContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showErrorMessage(message: String?)
        fun showErrorContainer()
        fun showRepositories(it: RepositoriesModel)
    }

    interface Presenter {
        fun getRepositories()
        fun dispose()
    }

    interface Repository {
        fun getRepositories(): Single<RepositoriesModel>
    }
}