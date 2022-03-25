@file:Suppress("FunctionMinLength")
package com.example.core.log

import com.example.core.BuildConfig
import timber.log.Timber

/**
 * Abstracts the logging implementation to a common interface.
 */
object Log {

    /**
     * Initializes the underlying logging framework.
     */
    fun init() {
        // Sets up Timber logging framework
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    /**
     * Debug log.
     *
     * @param message the log message
     * @param throwable an optional exception to log.
     */
    fun d(message: String, throwable: Throwable? = null) {
        Timber.d(throwable, message)
    }

    /**
     * Debug log.
     *
     * @param throwable the exception to be logged.
     */
    fun d(throwable: Throwable?) {
        Timber.d(throwable)
    }

    /**
     * Info log.
     *
     * @param message the log message
     * @param throwable an optional exception to log.
     */
    fun i(message: String, throwable: Throwable? = null) {
        Timber.i(throwable, message)
    }

    /**
     * Info log.
     *
     * @param throwable an optional exception to log.
     */
    fun i(throwable: Throwable?) {
        Timber.i(throwable)
    }

    /**
     * Warn log.
     *
     * @param message the log message
     * @param throwable an optional exception to log.
     */
    fun w(message: String, throwable: Throwable? = null) {
        Timber.w(throwable, message)
    }

    /**
     * Warn log.
     *
     * @param throwable the exception to be logged.
     */
    fun w(throwable: Throwable?) {
        Timber.w(throwable)
    }

    /**
     * Debug log.
     *
     * @param message the error message.
     * @param throwable the exception to be logged.
     */
    fun e(message: String, throwable: Throwable? = null) {
        Timber.e(throwable, message)
    }

    /**
     * Error log.
     *
     * @param throwable the exception to be logged.
     */
    fun e(throwable: Throwable?) {
        Timber.e(throwable)
    }
}
