package com.example.guilhermecardoso.magicroutines.searchresult

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.guilhermecardoso.magicroutines.R
import com.example.guilhermecardoso.magicroutines.base.BaseFragment
import com.example.guilhermecardoso.magicroutines.service.LoggingInterceptor
import com.example.guilhermecardoso.magicroutines.service.MagicAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SearchResultFragment: BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.search_result_fragment, container, false)

        val client = OkHttpClient.Builder().
                readTimeout(1, TimeUnit.MINUTES).
                connectTimeout(1, TimeUnit.MINUTES).
                addInterceptor(LoggingInterceptor()).build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.magicthegathering.io/")
                .client(client)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(MagicAPI::class.java)
        launch {
            val response = service.cards().await()
            Log.d("DEBUG", response.cards.first().toString())
        }

        return view
    }
}