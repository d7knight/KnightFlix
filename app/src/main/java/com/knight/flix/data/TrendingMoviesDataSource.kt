package com.knight.flix.data

import androidx.paging.ItemKeyedDataSource
import com.knight.flix.reactivex.Schedulers
import com.knight.flix.ui.model.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class TrendingMoviesDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val trendingMoviesRepository: TrendingMoviesRepository,
    private val schedulers: Schedulers
) : ItemKeyedDataSource<Int, Movie>() {
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Movie>) {
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Movie>) {
        trendingMoviesRepository.getWeeklyTrendingMovies(1)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    callback.onResult(it.movies, it.position, it.totalCount)
                }, onError = {
                    Timber.e(it, "Error loading initial page!")
                }).addTo(compositeDisposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Movie>) {
        val nextPage = params.key + 1
        trendingMoviesRepository.getWeeklyTrendingMovies(nextPage)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    callback.onResult(it.movies)
                }, onError = {
                    Timber.e(it, "Error loading next page $nextPage!")
                }).addTo(compositeDisposable)
    }

    override fun getKey(item: Movie): Int {
        return item.pageNumber
    }
}
