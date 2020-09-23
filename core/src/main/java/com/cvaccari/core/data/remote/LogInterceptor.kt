package com.cvaccari.core.data.remote

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object LogInterceptor {

    fun getInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}