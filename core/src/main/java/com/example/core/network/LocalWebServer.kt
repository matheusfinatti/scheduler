package com.example.core.network

import android.content.res.Resources
import android.os.StrictMode
import okhttp3.HttpUrl
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

/**
 * Local web server to serve the provided json.
 *
 * @property resources an instance of [Resources] from any [Context].
 */
@Suppress("BlockingMethodInNonBlockingContext")
class LocalWebServer(private val resources: Resources) {

    init {
        // enables networking on main thread as a workaround to use the mock web server.
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitNetwork()
                .build()
        )
    }

    private val server by lazy {
        MockWebServer()
    }

    /**
     * Server url.
     */
    val url: HttpUrl
        get() = server.url("/")

    /**
     * Initializes the web server.
     */
    @Suppress("MagicNumber", "UseIfInsteadOfWhen")
    fun init() {
        server.start()

        val json = resources.assets
            .open("response.json")
            .bufferedReader(charset = Charsets.UTF_8)
            .use { reader ->
                reader.readText()
            }

        val dispatcher = object : Dispatcher() {

            override fun dispatch(request: RecordedRequest): MockResponse =
                when (request.path) {
                    "/offices" -> MockResponse().setResponseCode(200).setBody(json)
                    else -> MockResponse().setResponseCode(404)
                }
        }

        server.dispatcher = dispatcher
    }
}
