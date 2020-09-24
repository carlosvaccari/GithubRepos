package com.cvaccari.features.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

abstract class BaseActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    var mToolbar: Toolbar? = null

    @IdRes
    protected abstract fun getToolBarResource(): Int

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    protected abstract fun initComponents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResource())

        mToolbar = findViewById(getToolBarResource())
        setSupportActionBar(mToolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        initComponents()
    }

}