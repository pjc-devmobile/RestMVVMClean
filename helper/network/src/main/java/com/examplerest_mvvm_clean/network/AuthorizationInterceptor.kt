package com.examplerest_mvvm_clean.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

internal class AuthorizationInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", CLIENT_ID)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val CLIENT_ID = "Client-ID 1ceddedc03a5d71"
    }
}