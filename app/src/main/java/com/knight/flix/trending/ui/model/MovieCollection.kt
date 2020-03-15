package com.knight.flix.trending.ui.model

data class MovieCollection(
    val movies: List<Movie>,
    val position: Int,
    val totalCount: Int
)
