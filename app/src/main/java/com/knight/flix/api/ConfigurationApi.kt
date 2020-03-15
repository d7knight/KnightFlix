package com.knight.flix.api

import com.knight.flix.api.model.Configuration
import io.reactivex.Single
import retrofit2.http.GET

interface ConfigurationApi {

    @GET("configuration")
    fun getConfiguration(): Single<Configuration>
}
