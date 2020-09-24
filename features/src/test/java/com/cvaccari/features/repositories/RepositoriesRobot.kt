package com.cvaccari.features.repositories

import androidx.test.core.app.ApplicationProvider
import com.cvaccari.features.R
import com.cvaccari.features.commons.TestApplication
import com.cvaccari.features.commons.launchFragment
import com.cvaccari.features.commons.viewAsserts
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class RepositoriesRobot {

    val repository: RepositoriesRepository = mockk()

    fun launch(robotCommands: RepositoriesFragment.() -> Unit) {
        setUpInjections()
        launchFragment(RepositoriesFragment()) {
            this.apply(robotCommands)
        }
    }

    private fun setUpInjections() {
        ApplicationProvider.getApplicationContext<TestApplication>().kodein.addConfig {
            bind<RepositoriesContract.Repository>(overrides = true) with provider {
                repository
            }
        }
    }

    fun injectSuccessfullRequest() {
        every { repository.getRepositories() } answers { Single.just(RepositoriesRepositoryHelper.successfulRepositoriesRequest) }
    }

    fun injectErrorRequest() {
        every { repository.getRepositories() } answers { Single.error(RepositoriesRepositoryHelper.error) }
    }

    fun checkListIsShown() {
        viewAsserts {
            R.id.recyclerview_repositories.isDisplayed()
        }
    }

    fun checkSnackbarIsShown() {
        viewAsserts {
            "Um erro aconteceu. Tente novamente mais tarde.".isDisplayed()
        }
    }
}