package com.cvaccari.features.pullrequests

import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.cvaccari.features.R
import com.cvaccari.features.SingleFragmentTestActivity
import com.cvaccari.features.commons.TestApplication
import com.cvaccari.features.commons.launchFragment
import com.cvaccari.features.commons.viewAsserts
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class PullRequestsRobot {

    val repository: PullRequestsRepository = mockk()

    fun launch(robotCommands: PullRequestsFragment.() -> Unit) {
        setUpInjections()
        launchFragment(PullRequestsFragment(), getDefaultIntent()) {
            this.apply(robotCommands)
        }
    }

    private fun getDefaultIntent(): Intent {
        val intent = Intent(
            InstrumentationRegistry.getInstrumentation().context,
            SingleFragmentTestActivity::class.java
        )
        val bundle = Bundle()
        bundle.putSerializable("requestModel", PullRequestsHelper.request)
        intent.putExtra("EXTRAS", bundle)
        return intent
    }


    private fun setUpInjections() {
        ApplicationProvider.getApplicationContext<TestApplication>().kodein.addConfig {
            bind<PullRequestsContract.Repository>(overrides = true) with provider {
                repository
            }
        }
    }

    fun injectSuccessfullRequest() {
        every { repository.getPullRequests(any()) } answers { Single.just(PullRequestsHelper.successfulPullRequestRequest) }
    }

    fun injectErrorRequest() {
        every { repository.getPullRequests(any()) } answers { Single.error(PullRequestsHelper.error) }
    }

    fun checkListIsShown() {
        viewAsserts {
            R.id.recyclerview_pull_requests.isDisplayed()
        }
    }

    fun checkSnackbarIsShown() {
        viewAsserts {
            "Um erro aconteceu. Tente novamente mais tarde.".isDisplayed()
        }
    }
}