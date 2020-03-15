package com.knight.flix.ui

import androidx.lifecycle.ViewModelProvider
import com.knight.flix.data.TrendingMoviesRepository
import com.knight.flix.ui.viewmodel.TrendingMoviesViewModel
import com.knight.flix.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TrendingMoviesModule {

    @Provides
    internal fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory {
        return viewModelFactory
    }
}