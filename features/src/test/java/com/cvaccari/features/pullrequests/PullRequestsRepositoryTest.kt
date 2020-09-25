package com.cvaccari.features.pullrequests

import com.cvaccari.features.pullrequests.PullRequestsHelper.currentPage
import com.cvaccari.features.pullrequests.PullRequestsHelper.request
import com.cvaccari.features.pullrequests.PullRequestsHelper.successfulPullRequestRequest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class PullRequestsRepositoryTest {

    private lateinit var repository: PullRequestsContract.Repository

    private var api : PullRequestsApi = mockk()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        repository = PullRequestsRepository(api)
    }

    @Test
    fun `request pull requests with success`() {
        val response = successfulPullRequestRequest
        val requestModel = request

        //Given
        every { api.getPullRequests(requestModel.owner.login, requestModel.repository, currentPage) } returns Single.just(response)

        //When
        val testObservable = repository.getPullRequests(requestModel).test()

        //then
        testObservable.assertValue { it == response }
        testObservable.assertNoErrors()
        testObservable.assertComplete()
    }

    @Test
    fun `request repositories with error`() {
        val response = PullRequestsHelper.error
        val requestModel = request

        //Given
        every { api.getPullRequests(requestModel.owner.login, requestModel.repository, currentPage) } returns Single.error(response)

        //When
        val testObservable = repository.getPullRequests(requestModel).test()

        //then
        testObservable.assertError { it == response }
        testObservable.assertNotComplete()
    }
}