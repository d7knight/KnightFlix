package com.knight.flix.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.knight.flix.R
import com.knight.flix.application.KnightFlixApplication
import com.knight.flix.injection.TrendingMoviesActivitySubcomponent
import com.knight.flix.ui.adapter.TrendingMoviesAdapter
import com.knight.flix.ui.viewmodel.TrendingMoviesViewModel
import javax.inject.Inject
import kotlinx.android.synthetic.main.trending_movies_activity.*

private const val GRID_SPAN_COUNT = 3

class TrendingMoviesActivity : AppCompatActivity() {

    private lateinit var trendingMoviesActivitySubcomponent: TrendingMoviesActivitySubcomponent
    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    private val trendingMoviesViewModel by lazy(LazyThreadSafetyMode.NONE) {
        viewModelProviderFactory.create(TrendingMoviesViewModel::class.java)
    }
    private val trendingMoviesAdapter = TrendingMoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trending_movies_activity)
        setupDagger()
        setupTrendingMoviesRecyclerView()
        observeTrendingMovies()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.trending_movies_menu, menu)
        val myActionMenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                trendingMoviesViewModel.onQueryTextChange(s)
                return false
            }
        })
        val searchQueryLiveData = trendingMoviesViewModel.observeSearchQuery()
        val searchQueryObserver = Observer<String> {
            searchQueryLiveData.removeObservers(this)
            myActionMenuItem.expandActionView()
            searchView.setQuery(it, true)
            searchView.clearFocus()
        }
        searchQueryLiveData.observe(this, searchQueryObserver)
        return true
    }

    private fun observeTrendingMovies() {
        trendingMoviesViewModel.observeTrendingMovies()
            .observe(this, Observer {
                trendingMoviesAdapter.submitList(it)
            })
    }

    private fun setupTrendingMoviesRecyclerView() {
        trendingMoviesRecycler.apply {
            adapter = trendingMoviesAdapter
            layoutManager = GridLayoutManager(
                context,
                GRID_SPAN_COUNT
            )
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupDagger() {
        trendingMoviesActivitySubcomponent = (application as KnightFlixApplication)
            .getAppComponent()
            .trendingMoviesActivitySubcomponentBuilder()
            .build()
        trendingMoviesActivitySubcomponent.inject(this)
    }
}
