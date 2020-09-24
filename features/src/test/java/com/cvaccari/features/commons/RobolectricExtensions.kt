package com.cvaccari.features.commons

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import com.cvaccari.features.SingleFragmentTestActivity
import com.cvaccari.features.base.BaseFragment

typealias OnActivityScope<T> = T.() -> Unit

fun <T : BaseFragment> launchFragment(
    fragment: T,
    intent: Intent? = null,
    block: OnActivityScope<T>
) {
    val scenario = when (intent) {
        null -> ActivityScenario.launch(SingleFragmentTestActivity::class.java)
        else -> ActivityScenario.launch(intent)
    }
    scenario.onActivity {
        it.setupFragment(fragment)
        block(fragment)
        it.finish()
    }
    scenario.close()
}