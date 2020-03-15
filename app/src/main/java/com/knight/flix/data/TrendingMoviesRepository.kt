package com.knight.flix.data

import com.knight.flix.api.TrendingApi
import com.knight.flix.api.model.Configuration
import com.knight.flix.mapper.MovieListMapper
import com.knight.flix.ui.model.MovieCollection
import com.knight.flix.ui.provider.SearchQueryProvider
import io.reactivex.Single
import io.reactivex.rxkotlin.Observables
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingMoviesRepository @Inject constructor(
    private val trendingApi: TrendingApi,
    private val configurationRepository: ConfigurationRepository,
    private val movieListMapper: MovieListMapper,
    private val searchQueryProvider: SearchQueryProvider
) {

    fun getWeeklyTrendingMovies(pageNumber: Int): Single<MovieCollection> {
        return Observables.combineLatest(
            configurationRepository.observeConfiguration(),
            searchQueryProvider.observeSearchQuery()
        ) { configuration, query ->
            Pair(configuration, query)
        }.firstOrError()
            .flatMap { getTrendingMovies(configuration = it.first, pageNumber = pageNumber, query = it.second) }
    }

    private fun getTrendingMovies(
        configuration: Configuration,
        pageNumber: Int,
        query: String
    ): Single<MovieCollection> {
        return if (query.isEmpty()) {
            trendingApi.getWeeklyTrendingMovies(pageNumber)
        } else {
            trendingApi.searchMovies(query, pageNumber)
        }.map {
            movieListMapper.mapToMovieCollection(configuration, it)
        }
    }
}
