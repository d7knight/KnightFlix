package com.knight.flix.ui.model

data class Movie(
    val id: String,
    val posterUrl: String,
    val backdropUrl: String,
    val title: String,
    val isVideo: Boolean,
    val pageNumber: Int,
    val thumbnailPosterUrl: String
)
