package com.knight.flix.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.knight.flix.data.TrendingMoviesPagingRepository
import com.knight.flix.ui.model.Movie
import com.knight.flix.ui.provider.SearchQueryProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.willReturn
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Observable
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.quality.Strictness


class TrendingMoviesViewModelTest {

    @get:Rule val mockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val trendingMoviesPagingRepository = mock<TrendingMoviesPagingRepository>()
    private val searchQueryProvider = mock<SearchQueryProvider>()
    private val trendingMoviesViewModel = TrendingMoviesViewModel(
        trendingMoviesPagingRepository = trendingMoviesPagingRepository,
        searchQueryProvider = searchQueryProvider
    )

    @Test
    fun observeTrendingMovies_shouldCallTrendingMoviesPagingRepository(){
        // When
        trendingMoviesViewModel.observeTrendingMovies()

        // Then
        then(trendingMoviesPagingRepository).should().getWeeklyTrendingMovies(any())
    }

    @Test
    fun onQueryTextChange_givenObservingTrendingMovies_shouldUpdateSearchQueryProvider(){
        // Given
        given { trendingMoviesPagingRepository.getWeeklyTrendingMovies(any()) } willReturn { mock() }
        trendingMoviesViewModel.observeTrendingMovies()
        val query = "query"

        // When
        trendingMoviesViewModel.onQueryTextChange(query)

        // Then
        then(searchQueryProvider).should().updateSearchQuery(query)
    }

    @Test
    fun observeSearchQuery_shouldObserveSearchQueryProvider(){
        // Given
        val query = "query"
        given { searchQueryProvider.observeSearchQuery() } willReturn { Observable.just(query) }

        // When
        trendingMoviesViewModel.observeSearchQuery()

        // Then
        then(searchQueryProvider).should().observeSearchQuery()
    }

    @Test
    fun observeSearchQuery_givenSearchQueryProviderEmitsQuery_shouldUpdateSearchLiveData(){
        // Given
        val query = "query"
        given { searchQueryProvider.observeSearchQuery() } willReturn { Observable.just(query) }

        // When
        val liveData = trendingMoviesViewModel.observeSearchQuery()

        // Then
        liveData.observeForever {
            assertThat(it).isEqualTo(query)
        }
    }

    @Test
    fun onQueryTextChange_givengivenObservingTrendingMovies_shouldInvalidateDataSource(){
        // Given
        val query = "query"
        val livedata = mock<LiveData<PagedList<Movie>>>()
        val pagedList = mock<PagedList<Movie>>()
        val dataSource = mock<DataSource<Int, Movie>>()
        given { livedata.value } willReturn { pagedList }
        given { pagedList.dataSource } willReturn { dataSource }
        given { trendingMoviesPagingRepository.getWeeklyTrendingMovies(any()) } willReturn { livedata }
        trendingMoviesViewModel.observeTrendingMovies()

        // When
        trendingMoviesViewModel.onQueryTextChange(query)

        // Then
        then(dataSource).should().invalidate()
    }
}