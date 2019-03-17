package com.knight.flix.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.knight.flix.data.ConfigurationRepository
import com.knight.flix.data.TrendingMoviesRepository
import com.knight.flix.api.model.TrendingMoviePage
import com.knight.flix.data.TrendingMoviesPagingRepository
import com.knight.flix.reactivex.Schedulers
import com.knight.flix.ui.model.Movie
import com.knight.flix.ui.provider.SearchQueryProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class TrendingMoviesViewModel @Inject constructor(
    private val trendingMoviesPagingRepository: TrendingMoviesPagingRepository,
    private val searchQueryProvider: SearchQueryProvider
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var pagedList: LiveData<PagedList<Movie>>
    private val searchQuery by lazy(LazyThreadSafetyMode.NONE) {
        LiveDataReactiveStreams.fromPublisher(
            searchQueryProvider.observeSearchQuery()
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun observeTrendingMovies(): LiveData<PagedList<Movie>> {
        return trendingMoviesPagingRepository.getWeeklyTrendingMovies(compositeDisposable)
            .also { pagedList = it }
    }

    fun observeSearchQuery() = searchQuery

    fun onQueryTextChange(query: String) {
        searchQueryProvider.updateSearchQuery(query)
        pagedList.value?.dataSource?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}