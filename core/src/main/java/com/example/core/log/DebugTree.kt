@file:Suppress("ThrowingExceptionsWithoutMessageOrCause")
package com.example.core.log

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import timber.log.Timber
import java.util.regex.Pattern
import kotlin.math.min

/**
 * Basically a copy of [Timber]'s [timber.log.Timber.DebugTree] but modified to get the correct
 * tag when logging is called from [Log] class by getting a previous
 * item in the call stack.
 */
class DebugTree : Timber.Tree() {

    /**
     * Extract the tag which should be used for the message from the `element`. By default
     * this will use the class name without any anonymous class suffixes (e.g., `Foo$1`
     * becomes `Foo`).
     *
     * Note: This will not be called if a [manual tag][.tag] was specified.
     */
    private fun createStackElementTag(element: StackTraceElement): String? {
        var tag = element.className
        val m = anonymousClass.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1)
        // Tag length limit was removed in API 24.
        return if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            "$PREFIX$tag"
        } else {
            "$PREFIX${tag.substring(0,
                MAX_TAG_LENGTH
            )}"
        }
    }

    private fun getTag(): String? {
        val stackTrace = Throwable().stackTrace
        check(stackTrace.size > CALL_STACK_INDEX) {
            throw IllegalStateException(
                "Synthetic stacktrace didn't have enough elements: are you using proguard?"
            )
        }

        return createStackElementTag(stackTrace[CALL_STACK_INDEX])
    }

    /**
     * Break up `message` into maximum-length chunks (if needed) and send to either
     * [Log.println()][Log.println] or
     * [Log.wtf()][Log.wtf] for logging.
     *
     * {@inheritDoc}
     */
    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logtag = tag ?: getTag()

        if (message.length < MAX_LOG_LENGTH) {
            if (priority == Log.ASSERT) {
                Log.wtf(logtag, message)
            } else {
                Log.println(priority, logtag, message)
            }
            return
        }

        // Split by line, then ensure each line can fit into Log's maximum length.
        splitByLine(priority, logtag, message)
    }

    @SuppressLint("LogNotTimber")
    private fun splitByLine(priority: Int, tag: String?, message: String) {
        var i = 0
        val length = message.length
        while (i < length) {
            var newline = message.indexOf('\n', i)
            newline = if (newline != -1) newline else length
            do {
                val end = min(newline, i + MAX_LOG_LENGTH)
                val part = message.substring(i, end)
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, part)
                } else {
                    Log.println(priority, tag, part)
                }
                i = end
            } while (i < newline)
            i++
        }
    }

    companion object {
        private const val MAX_LOG_LENGTH = 4000
        private const val MAX_TAG_LENGTH = 23
        private const val CALL_STACK_INDEX = 8
        private const val PREFIX = "SCHEDULER."
        private val anonymousClass = Pattern.compile("(\\$\\d+)+$")
    }
}
