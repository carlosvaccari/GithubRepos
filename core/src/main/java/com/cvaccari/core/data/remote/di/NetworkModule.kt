package com.cvaccari.core.data.remote.di

import com.cvaccari.core.BuildConfig.BASE_URL
import com.cvaccari.core.BuildConfig.DEBUG
import com.cvaccari.core.data.remote.LogInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val LOG_INTERCEPTOR = "LOG_INTERCEPTOR"

val networkModule = Kodein.Module("networkModule") {
    bind<Retrofit>() with provider {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(instance())
            .addCallAdapterFactory(instance())
            .client(instance())
            .build()
    }

    bind<CallAdapter.Factory>() with provider { RxJava2CallAdapterFactory.create() }

    bind<Interceptor>(tag = LOG_INTERCEPTOR) with provider {
        LogInterceptor.getInterceptor()
    }

    bind<OkHttpClient>() with provider {
        val builder = OkHttpClient.Builder()

        if (DEBUG) {
            builder.addInterceptor(instance(LOG_INTERCEPTOR))
        }

        builder.build()
    }

    bind<Converter.Factory>() with provider { GsonConverterFactory.create() }

}