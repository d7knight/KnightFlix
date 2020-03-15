package com.knight.flix.mapper

import com.knight.flix.api.model.Configuration
import com.knight.flix.api.model.TrendingMoviePage
import com.knight.flix.trending.ui.model.Movie
import com.knight.flix.trending.ui.model.MovieCollection
import javax.inject.Inject

private const val ORIGINAL_PATH_PARAM = "original"

class MovieListMapper @Inject constructor() {

    fun mapToMovieCollection(configuration: Configuration, trendingMoviePage: TrendingMoviePage): MovieCollection {

        return MovieCollection(
            movies = trendingMoviePage.trendingMovieList.map {
                val lowResPathParam = configuration.images.posterSizes.first()

                Movie(
                    id = it.id,
                    backdropUrl = configuration.images.secureBaseUrl + ORIGINAL_PATH_PARAM + it.backdropPath,
                    posterUrl = configuration.images.secureBaseUrl + ORIGINAL_PATH_PARAM + it.posterPath,
                    thumbnailPosterUrl = configuration.images.secureBaseUrl + lowResPathParam + it.backdropPath,
                    title = it.title,
                    isVideo = it.isVideo,
                    pageNumber = trendingMoviePage.page
                )
            },
            totalCount = trendingMoviePage.totalResults,
            position = (trendingMoviePage.page - 1) * trendingMoviePage.trendingMovieList.size
        )
    }
}
