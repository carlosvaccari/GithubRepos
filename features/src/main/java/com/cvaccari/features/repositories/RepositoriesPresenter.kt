package com.cvaccari.features.repositories

import io.reactivex.disposables.Disposable

class RepositoriesPresenter(
    val view: RepositoriesContract.View,
    val repository: RepositoriesContract.Repository
) : RepositoriesContract.Presenter {

    var disposable: Disposable? = null

    override fun getRepositories(isLoadingMore: Boolean) {
        disposable = repository.getRepositories()
            .doOnSubscribe { showLoading(isLoadingMore) }
            .doFinally { hideLoading(isLoadingMore) }
            .subscribe({
                view.showRepositories(it)
            }, {
                view.showErrorContainer()
            })
    }

    private fun hideLoading(isLoadingMore: Boolean) = if (isLoadingMore) view.hideProgressBar() else view.hideLoading()

    private fun showLoading(isLoadingMore: Boolean) = if(isLoadingMore) view.showProgressBar() else view.showLoading()

    override fun dispose() {
        disposable?.dispose()
    }
}