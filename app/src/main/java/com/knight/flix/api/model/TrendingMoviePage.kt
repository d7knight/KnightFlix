package com.knight.flix.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingMoviePage(
    val page: Int,
    @Json(name = "results")
    val trendingMovieList: List<TrendingMovie>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)