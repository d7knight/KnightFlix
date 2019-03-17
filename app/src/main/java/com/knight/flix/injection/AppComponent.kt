package com.knight.flix.injection

import com.knight.flix.application.ApplicationModule
import com.knight.flix.application.KnightFlixApplication
import com.knight.flix.network.module.NetworkModule
import com.knight.flix.reactivex.SchedulersModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SchedulersModule::class, ApplicationModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(knightFlixApplication: KnightFlixApplication): AppComponent.Builder

        fun build(): AppComponent
    }

    fun trendingMoviesActivitySubcomponentBuilder(): TrendingMoviesActivitySubcomponent.Builder

    fun inject(knightFlixApplication: KnightFlixApplication)
}