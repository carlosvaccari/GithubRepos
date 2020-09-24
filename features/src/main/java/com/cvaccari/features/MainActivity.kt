package com.cvaccari.features

import com.cvaccari.features.base.BaseActivity
import com.cvaccari.features.commons.extensions.goTo
import com.cvaccari.features.commons.extensions.shouldCloseActivity

class MainActivity: BaseActivity() {

    override fun getToolBarResource() = 0 //TODO

    override fun getLayoutResource() = R.layout.activity_main

    override fun initComponents() {
        goTo(R.id.fragment_repositories_list)
    }

    override fun onBackPressed() {
        when (shouldCloseActivity()) {
            true -> finish()
            else -> super.onBackPressed()
        }
    }
}