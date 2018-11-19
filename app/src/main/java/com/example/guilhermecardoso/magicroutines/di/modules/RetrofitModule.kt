package com.example.guilhermecardoso.magicroutines.di.modules

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module(includes = [ContextModule::class])
class RetrofitModule {

    @Provides
    fun providesRetrofit(baseURL: String, httpClient: OkHttpClient, adapterFactory: CallAdapter.Factory, converterFactory: Converter.Factory): Retrofit =
            Retrofit
                    .Builder()
                    .baseUrl(baseURL)
                    .client(httpClient)
                    .addCallAdapterFactory(adapterFactory)
                    .addConverterFactory(converterFactory)
                    .build()

    @Provides
    fun providesHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient =
            OkHttpClient
                    .Builder()
                    .readTimeout(1, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .cache(cache)
                    .build()


    @Provides
    fun providesCallAdapterFactory(): CallAdapter.Factory = CoroutineCallAdapterFactory()

    @Provides
    fun providesConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    fun providesInterceptor(): Interceptor = HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger { Timber.d(it) }
    ).apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    fun providesCache(context: Context): Cache = Cache(context.cacheDir, 5 * 1024 * 1024)

    @Provides
    fun providesBaseURL(): String = "http://api.magicthegathering.io/"
}