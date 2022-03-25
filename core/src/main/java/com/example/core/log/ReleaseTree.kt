package com.example.core.log

import android.util.Log
import timber.log.Timber

/**
 * A logging tree for [Timber] that logs important information for release builds.
 */
class ReleaseTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int) = priority >= Log.INFO

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logtag = tag ?: TAG

        if (isLoggable(logtag, priority)) {
            if (t != null) {
                Log.println(priority, logtag, "$message\n$t")
            } else {
                Log.println(priority, logtag, message)
            }
        }
    }

    companion object {
        private const val TAG = "SCHEDULER"
    }
}
