package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.PullRequestsHelper.request
import com.cvaccari.features.pullrequests.PullRequestsHelper.successfulPullRequestRequest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PullRequestsPresenterTest {

    private var view: PullRequestsContract.View = mockk(relaxed = true)

    private var repository: PullRequestsContract.Repository = mockk()

    private lateinit var presenter: PullRequestsContract.Presenter

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        presenter = PullRequestsPresenter(view, repository)
    }

    @Test
    fun `request pull requests with success, should show items`() {
        val requestModel = request

        every { repository.getPullRequests(any()) } returns Single.just(successfulPullRequestRequest)

        presenter.getPullRequests(requestModel)

        verify(exactly = 1) {
            view.showLoading()
            view.hideLoading()
            view.showPullRequests(any())
        }

        verify(exactly = 0) {
            view.showErrorContainer()
        }
    }

    @Test
    fun `request pull requests types with error, should show error container`() {
        val requestModel = request

        every { repository.getPullRequests(any()) } returns Single.error(PullRequestsHelper.error)

        presenter.getPullRequests(requestModel)

        verify(exactly = 1) {
            view.showLoading()
            view.hideLoading()
            view.showErrorContainer()
        }

        verify(exactly = 0) {
            view.showPullRequests(any())
        }
    }

}