package com.knight.flix.data

import com.knight.flix.api.ConfigurationApi
import com.knight.flix.api.model.Configuration
import com.knight.flix.reactivex.Schedulers
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigurationRepository @Inject constructor(
    private val configurationApi: ConfigurationApi,
    private val schedulers: Schedulers
) {

    private val subject = BehaviorSubject.create<Configuration>()

    fun loadConfiguration() {
        configurationApi.getConfiguration()
            .subscribeOn(schedulers.io())
            .subscribeBy(
                onSuccess = {
                    subject.onNext(it)
                },
                onError = {
                    Timber.e(it, "Error loading configuration!")
                }
            ).let { /* Intentional No-op */ }
    }

    fun observeConfiguration(): Observable<Configuration> {
        return subject.hide()
    }

}