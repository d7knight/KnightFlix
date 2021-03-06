package com.knight.flix.trending.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.knight.flix.data.TrendingMoviesPagingRepository
import com.knight.flix.trending.ui.model.Movie
import com.knight.flix.trending.ui.provider.SearchQueryProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
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
