package com.knight.flix.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingMovie(
    val id: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    val title: String,
    @Json(name = "video")
    val isVideo: Boolean
)