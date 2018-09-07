package com.example.guilhermecardoso.magicroutines.service

import android.util.Log
import kotlinx.coroutines.experimental.Deferred
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.GET
import java.io.IOException

interface MagicAPI {
    @GET("/v1/cards")
    fun cards(): Deferred<ResponseCards>
}

data class Card(val name: String, val manaCost: String)
data class ResponseCards(val cards: List<Card>)

internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        Log.d("INTERCEPTOR",String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()))

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        Log.d("INTERCEPTOR",String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()))

        return response
    }
}