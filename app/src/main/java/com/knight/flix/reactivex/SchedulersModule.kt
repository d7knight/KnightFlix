package com.knight.flix.reactivex

import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {

    @Provides
    internal fun provideSchedulers(androidSchedulers: AndroidSchedulers): Schedulers {
        return androidSchedulers
    }
}
