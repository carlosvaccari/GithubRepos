package com.cvaccari.features.repositories

import com.cvaccari.features.repositories.RepositoriesRepositoryHelper.currentPage
import com.cvaccari.features.repositories.RepositoriesRepositoryHelper.successfulRepositoriesRequest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class RepositoriesRepositoryTest {

    private lateinit var repository: RepositoriesContract.Repository

    private var api : RepositoriesApi = mockk()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        repository = RepositoriesRepository(api)
    }

    @Test
    fun `request repositories with success`() {
        val response = successfulRepositoriesRequest

        //Given
        every { api.getRepositories(any(), currentPage) } returns Single.just(response)

        //When
        val testObservable = repository.getRepositories().test()

        //then
        testObservable.assertValue { it == response }
        testObservable.assertNoErrors()
        testObservable.assertComplete()
    }

    @Test
    fun `request repositories with error`() {
        val response = RepositoriesRepositoryHelper.error

        //Given
        every { api.getRepositories(any(), currentPage) } returns Single.error(response)

        //When
        val testObservable = repository.getRepositories().test()

        //then
        testObservable.assertError { it == response }
        testObservable.assertNotComplete()
    }
}