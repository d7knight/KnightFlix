package com.knight.flix.trending.ui

import androidx.lifecycle.ViewModelProvider
import com.knight.flix.trending.ui.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TrendingMoviesModule {

    @Provides
    internal fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory {
        return viewModelFactory
    }
}
