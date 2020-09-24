package com.cvaccari.features.repositories

import io.reactivex.disposables.Disposable

class RepositoriesPresenter(
    val view: RepositoriesContract.View,
    val repository: RepositoriesContract.Repository
) : RepositoriesContract.Presenter {

    var disposable: Disposable? = null

    override fun getRepositories() {
        disposable = repository.getRepositories()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({
                view.showRepositories(it)
            }, {
                view.showErrorMessage(it.message)
            })
    }

    override fun dispose() {
        disposable?.dispose()
    }
}