package com.knight.flix.ui.model

data class MovieCollection(
    val movies: List<Movie>,
    val position: Int,
    val totalCount: Int
)