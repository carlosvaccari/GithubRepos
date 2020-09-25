package com.cvaccari.features

import com.cvaccari.features.base.BaseActivity
import com.cvaccari.features.commons.extensions.goTo
import com.cvaccari.features.commons.extensions.shouldCloseActivity
import kotlinx.android.synthetic.main.content_appbar_layout.*

class MainActivity: BaseActivity() {

    override fun getToolBarResource() = R.id.toolbar_main

    override fun getLayoutResource() = R.layout.activity_main

    override fun initComponents() {
        initListeners()
        goTo(R.id.fragment_repositories_list)
    }

    private fun initListeners() {
        imageview_menu_icon.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        when (shouldCloseActivity()) {
            true -> finish()
            else -> super.onBackPressed()
        }
    }
}