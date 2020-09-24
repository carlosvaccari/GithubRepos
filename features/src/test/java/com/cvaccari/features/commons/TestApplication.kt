package com.cvaccari.features.commons

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.cvaccari.core.CoreConstants.CONTEXT_TAG
import com.cvaccari.core.data.remote.di.networkModule
import com.cvaccari.features.repositories.di.repositoriesModule
import io.mockk.every
import io.mockk.mockk
import org.kodein.di.KodeinAware
import org.kodein.di.conf.ConfigurableKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

open class TestApplication : Application(), KodeinAware {

    override val kodein = ConfigurableKodein(mutable = true)

    init {
        val context = mockk<Context>(relaxed = true)
        val sharedPreferences = mockk<SharedPreferences>(relaxed = true)
        every { context.getSharedPreferences(any(), any()) } returns sharedPreferences

        kodein.addConfig {
            bind<Context>(tag = CONTEXT_TAG) with provider { this@TestApplication }
            import(networkModule)
            import(repositoriesModule)
        }
    }
}