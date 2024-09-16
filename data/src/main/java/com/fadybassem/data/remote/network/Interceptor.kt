package com.fadybassem.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class Interceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request().newBuilder().header("Accept", "*/*")
                .header("Accept-Encoding", "gzip,defla,br").header("Connection", "close")
                .header("accept", "application/json")
            return chain.proceed(request.build())
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}