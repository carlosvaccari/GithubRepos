package com.cvaccari.features.commons

import io.reactivex.Single

open class PageController {

    var currentPage = FIRST_PAGE

    open fun <T> registerObservable(single: Single<T>): Single<T> {
        return single.doOnSubscribe { currentPage++ }.doOnError { currentPage-- }
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}