package com.cvaccari.features.repositories

import com.cvaccari.features.repositories.RepositoriesRepositoryHelper.successfulRepositoriesRequest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RepositoriesPresenterTest {

    private var view: RepositoriesContract.View = mockk(relaxed = true)

    private var repository: RepositoriesContract.Repository = mockk()

    private lateinit var presenter: RepositoriesContract.Presenter

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter = RepositoriesPresenter(view, repository)
    }

    @Test
    fun `request repositories with success, should show items`() {
        every { repository.getRepositories() } returns Single.just(successfulRepositoriesRequest)

        presenter.getRepositories()

        verify(exactly = 1) {
            view.showLoading()
            view.hideLoading()
            view.showRepositories(any())
        }

        verify(exactly = 0) {
            view.showErrorContainer()
        }
    }

    @Test
    fun `request repositories with error, should show error container`() {
        every { repository.getRepositories() } returns Single.error(RepositoriesRepositoryHelper.error)

        presenter.getRepositories()

        verify(exactly = 1) {
            view.showLoading()
            view.hideLoading()
            view.showErrorContainer()
        }

        verify(exactly = 0) {
            view.showRepositories(any())
        }
    }

}