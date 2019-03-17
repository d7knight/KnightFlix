package com.knight.flix.injection

import com.knight.flix.ui.TrendingMoviesActivity
import com.knight.flix.ui.TrendingMoviesModule
import dagger.Subcomponent

@Subcomponent(modules = [TrendingMoviesModule::class])
interface TrendingMoviesActivitySubcomponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): TrendingMoviesActivitySubcomponent
    }

    fun inject(trendingMoviesActivity: TrendingMoviesActivity)
}