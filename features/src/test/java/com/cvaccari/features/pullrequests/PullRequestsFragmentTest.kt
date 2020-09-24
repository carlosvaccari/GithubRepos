package com.cvaccari.features.pullrequests

import android.os.Build
import com.cvaccari.features.commons.TestApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = TestApplication::class)
class PullRequestsFragmentTest {

    fun robot(func: PullRequestsRobot.() -> Unit) = PullRequestsRobot().apply { func() }

    @Test
    fun `when requesting pull requests with success, should show infos`() {
        robot {
            injectSuccessfullRequest()
            launch {
                checkListIsShown()
            }
        }
    }

    @Test
    fun `when requesting pull requests with error, should show error message`() {
        robot {
            injectErrorRequest()
            launch {
                checkSnackbarIsShown()
            }
        }
    }

}