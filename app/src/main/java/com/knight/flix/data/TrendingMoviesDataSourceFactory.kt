package com.knight.flix.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.knight.flix.reactivex.Schedulers
import com.knight.flix.ui.model.Movie
import io.reactivex.disposables.CompositeDisposable

class TrendingMoviesDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val trendingMoviesRepository: TrendingMoviesRepository,
    private val schedulers: Schedulers
) : DataSource.Factory<Int, Movie>() {

    private val usersDataSourceLiveData = MutableLiveData<TrendingMoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val trendingMoviesDataSource = TrendingMoviesDataSource(
            compositeDisposable,
            trendingMoviesRepository,
            schedulers
        )
        usersDataSourceLiveData.postValue(trendingMoviesDataSource)
        return trendingMoviesDataSource
    }
}