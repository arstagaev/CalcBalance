package com.arstagaev.calcbalance

import android.app.Application
import timber.log.Timber

class CalcBalanceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // init Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.tag("calc_b_timber")
        }

    }
}