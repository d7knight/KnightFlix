package com.knight.flix.network.module

import com.knight.flix.api.ConfigurationApi
import com.knight.flix.api.MovieDBInterceptor
import com.knight.flix.api.TrendingApi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

@Module
class NetworkModule {

    @Provides
    internal fun provideTrendingApi(retrofit: Retrofit): TrendingApi {
        return retrofit.create(TrendingApi::class.java)
    }

    @Provides
    internal fun provideConfigurationApi(retrofit: Retrofit): ConfigurationApi {
        return retrofit.create(ConfigurationApi::class.java)
    }

    @Provides
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    internal fun provideOkhttpClient(movieDBInterceptor: MovieDBInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(movieDBInterceptor).build()
    }
}