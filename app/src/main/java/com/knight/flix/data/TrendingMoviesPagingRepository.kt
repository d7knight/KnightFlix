package com.knight.flix.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.knight.flix.reactivex.Schedulers
import com.knight.flix.ui.model.Movie
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

private const val PAGE_SIZE = 15

@Singleton
class TrendingMoviesPagingRepository @Inject constructor(
    private val trendingMoviesRepository: TrendingMoviesRepository,
    private val schedulers: Schedulers
) {

    fun getWeeklyTrendingMovies(compositeDisposable: CompositeDisposable): LiveData<PagedList<Movie>> {
        val trendingMoviesDataSourceFactory = TrendingMoviesDataSourceFactory(
            compositeDisposable,
            trendingMoviesRepository,
            schedulers
        )
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .setEnablePlaceholders(false)
            .build()
        return LivePagedListBuilder<Int, Movie>(trendingMoviesDataSourceFactory, pagedListConfig).build()
    }

}