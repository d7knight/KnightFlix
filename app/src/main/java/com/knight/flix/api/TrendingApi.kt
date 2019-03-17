package com.knight.flix.api

import com.knight.flix.api.model.TrendingMoviePage
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingApi {

    @GET("trending/movie/week")
    fun getWeeklyTrendingMovies(@Query("page") pageNumber: Int): Single<TrendingMoviePage>


    @GET("search/movie/")
    fun searchMovies(@Query("query") query: String, @Query("page") page: Int): Single<TrendingMoviePage>

}