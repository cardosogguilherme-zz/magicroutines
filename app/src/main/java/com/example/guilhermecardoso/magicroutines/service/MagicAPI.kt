package com.example.guilhermecardoso.magicroutines.service

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit

interface MagicAPI {
    @GET("/v1/cards")
    fun cards(@QueryMap fields: Map<String, String>? = null): Deferred<ResponseCards>
}

//private val client = OkHttpClient.Builder().
//        readTimeout(1, TimeUnit.MINUTES).
//        connectTimeout(1, TimeUnit.MINUTES).
//        addInterceptor(LoggingInterceptor()).build()
//
//private val retrofit = Retrofit.Builder()
//        .baseUrl("http://api.magicthegathering.io/")
//        .client(client)
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//val service = retrofit.create(MagicAPI::class.java)!!

data class Card(val name: String, val set: String, val setName: String, val manaCost: String)
data class ResponseCards(val cards: List<Card>)

internal class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        Timber.d(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()))

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        Timber.d(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()))

        return response
    }
}