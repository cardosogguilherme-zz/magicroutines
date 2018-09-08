package com.example.guilhermecardoso.magicroutines.search

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.guilhermecardoso.magicroutines.R
import com.example.guilhermecardoso.magicroutines.base.BaseFragment
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultFragment
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultPresenter
import com.example.guilhermecardoso.magicroutines.service.LoggingInterceptor
import com.example.guilhermecardoso.magicroutines.service.MagicAPI
import com.example.guilhermecardoso.magicroutines.service.service
import com.example.guilhermecardoso.magicroutines.whenNonEmpty
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SearchFragment: BaseFragment(), SearchContract.View {
    lateinit var nameText: EditText

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.search_fragment, container, false)

        view.findViewById<TextView>(R.id.toolbar_title).text = "Card Search"

        nameText = view.findViewById(R.id.edit_name)
        val searchButton = view.findViewById<ImageView>(R.id.search_button)
        searchButton.setOnClickListener {
            Snackbar.make(view!!, "clicked", Snackbar.LENGTH_SHORT).show()
            searchPerformed(nameText.text.toString())
        }

        return view
    }

    override fun showSearchError() {
        Snackbar.make(view!!, "An internet error occured", Snackbar.LENGTH_SHORT).show()
    }

    override fun searchPerformed(term: String) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.let {
            val presenter = SearchResultPresenter(service)
            val fields = getFields()

            val fragment = SearchResultFragment.createFragment(presenter, fields)
            presenter.attachView(fragment)
            it.addToBackStack(this.javaClass.canonicalName)
            it.replace(R.id.fragment_container, fragment, SearchResultFragment::javaClass.name)
        }
        fragmentTransaction?.commit()
    }

    private fun getFields(): HashMap<String, String> {
        val fields = HashMap<String, String>()

        nameText.text.toString().whenNonEmpty { fields.put("name", value = this) }
        return fields
    }
}