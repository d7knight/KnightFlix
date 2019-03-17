package com.knight.flix.application

import android.content.res.Resources
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    internal fun provideResources(knightFlixApplication : KnightFlixApplication): Resources {
        return knightFlixApplication.resources
    }

}