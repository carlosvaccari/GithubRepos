package com.cvaccari.features

import androidx.annotation.RestrictTo
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cvaccari.features.base.BaseActivity
import com.cvaccari.features.base.BindableBaseFragment

@RestrictTo(RestrictTo.Scope.TESTS)
class SingleFragmentTestActivity : BaseActivity() {

    companion object {
        const val EXTRAS = "EXTRAS"
    }

    override fun getToolBarResource() = 0

    override fun getLayoutResource() = R.layout.activity_single_fragment_test

    override fun initComponents() {
    }

    fun setupFragment(fragment: Fragment) {
        val fm = supportFragmentManager

        if (intent.hasExtra(EXTRAS)) {
            fragment.arguments = intent.getBundleExtra(EXTRAS)
        }

        fm.beginTransaction()
            .replace(R.id.fragment_host, fragment)
            .commit()
    }
}
