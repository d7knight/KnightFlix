package com.knight.flix.api

import android.content.res.Resources
import com.knight.flix.R
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY_PARAM_KEY = "api_key"

class MovieDBInterceptor @Inject constructor(private val resources: Resources) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY_PARAM_KEY, resources.getString(R.string.api_key))
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
