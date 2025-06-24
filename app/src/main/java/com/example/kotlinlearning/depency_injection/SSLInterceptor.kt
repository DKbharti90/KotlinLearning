package com.example.kotlinlearning.depency_injection

import okhttp3.Interceptor
import okhttp3.Response

class SSLInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
        return chain.proceed(request)
    }
}