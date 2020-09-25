package com.cvaccari.features.repositories

import android.os.Build
import com.cvaccari.features.commons.TestApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(sdk = [Build.VERSION_CODES.P], application = TestApplication::class)
class RepositoriesFragmentTest {

    fun robot(func: RepositoriesRobot.() -> Unit) = RepositoriesRobot().apply { func() }

    @Test
    fun `when requesting repositories with success, should show infos`() {
        robot {
            injectSuccessfullRequest()
            launch {
                checkListIsShown()
            }
        }
    }

    @Test
    fun `when requesting repositories with error, should show error message`() {
        robot {
            injectErrorRequest()
            launch {
                checkSnackbarIsShown()
            }
        }
    }

}