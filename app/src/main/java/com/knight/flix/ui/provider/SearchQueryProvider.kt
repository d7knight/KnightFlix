package com.knight.flix.ui.provider

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchQueryProvider @Inject constructor() {

    private val subject = BehaviorSubject.createDefault("")

    fun observeSearchQuery(): Observable<String> {
        return subject.hide()
    }

    fun updateSearchQuery(query: String) {
        subject.onNext(query)
    }
}
