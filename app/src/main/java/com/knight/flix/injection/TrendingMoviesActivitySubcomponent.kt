package com.knight.flix.injection

import com.knight.flix.trending.ui.TrendingMoviesActivity
import com.knight.flix.trending.ui.TrendingMoviesModule
import dagger.Subcomponent

@Subcomponent(modules = [TrendingMoviesModule::class])
interface TrendingMoviesActivitySubcomponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): TrendingMoviesActivitySubcomponent
    }

    fun inject(trendingMoviesActivity: TrendingMoviesActivity)
}
