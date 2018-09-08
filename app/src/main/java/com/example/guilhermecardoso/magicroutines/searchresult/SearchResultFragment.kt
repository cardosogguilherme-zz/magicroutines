package com.example.guilhermecardoso.magicroutines.searchresult

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guilhermecardoso.magicroutines.R
import com.example.guilhermecardoso.magicroutines.base.BaseFragment
import com.example.guilhermecardoso.magicroutines.service.Card
import com.example.guilhermecardoso.magicroutines.service.LoggingInterceptor
import com.example.guilhermecardoso.magicroutines.service.MagicAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SearchResultFragment: BaseFragment(), SearchResultContract.View {
    lateinit var presenter: SearchResultContract.Presenter
    lateinit var recyclerview: RecyclerView

    companion object {
        val KEY_FIELDS: String = "KEY_FIELDS"

        fun createFragment(presenter: SearchResultPresenter, fields: HashMap<String, String>): SearchResultFragment {
            val fragment = SearchResultFragment()
            fragment.presenter = presenter

            val arguments = Bundle()
            arguments.putSerializable(KEY_FIELDS, fields)
            fragment.arguments = arguments

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.search_result_fragment, container, false)

        recyclerview = view.findViewById(R.id.recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(context!!)

        launch {
            val fields: HashMap<String, String> = arguments?.getSerializable(KEY_FIELDS) as HashMap<String, String>?
                    ?: HashMap()
            presenter.performSearch(fields)
        }

        return view
    }

    override fun showResults(cards: List<Card>) {
        recyclerview.adapter = SearchResultAdapter(this.context!!, cards.distinctBy { it.name })
    }
}