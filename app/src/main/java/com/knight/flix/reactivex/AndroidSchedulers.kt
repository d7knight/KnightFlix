package com.knight.flix.reactivex

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class AndroidSchedulers @Inject constructor() : Schedulers {
    override fun io(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}