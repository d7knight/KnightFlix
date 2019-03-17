package com.knight.flix.reactivex

import io.reactivex.Scheduler

interface Schedulers {
    fun io(): Scheduler
    fun mainThread(): Scheduler
}