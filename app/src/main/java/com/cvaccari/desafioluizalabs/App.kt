package com.cvaccari.desafioluizalabs

import android.app.Application
import android.content.Context
import com.cvaccari.core.CoreConstants.CONTEXT_TAG
import com.cvaccari.core.data.remote.di.networkModule
import org.kodein.di.KodeinAware
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class App : Application(), KodeinAware {

    override val kodein = ConfigurableKodein()

    init {
        kodein.addConfig {
            bind<Context>(tag = CONTEXT_TAG) with provider { this@App }
            import(networkModule)
        }
    }
}