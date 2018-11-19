package com.example.guilhermecardoso.magicroutines.searchresult

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.guilhermecardoso.magicroutines.App
import com.example.guilhermecardoso.magicroutines.R
import com.example.guilhermecardoso.magicroutines.base.BaseFragment
import com.example.guilhermecardoso.magicroutines.di.buildPresenter
import com.example.guilhermecardoso.magicroutines.service.Card
import com.example.guilhermecardoso.magicroutines.util.InfiniteScrollListener
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class SearchResultFragment: BaseFragment(), SearchResultContract.View {
    lateinit var presenter: SearchResultContract.Presenter
    lateinit var recyclerview: RecyclerView

    companion object {
        val KEY_FIELDS: String = "KEY_FIELDS"

        fun createFragment(fields: HashMap<String, String>): SearchResultFragment {
            val fragment = SearchResultFragment()

            val arguments = Bundle()
            arguments.putSerializable(KEY_FIELDS, fields)
            fragment.arguments = arguments

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflateFragment(R.layout.search_result_fragment, inflater)
        (activity?.application as App).appComponent.inject(this)
        presenter.attachView(this)

        view.findViewById<TextView>(R.id.toolbar_title).text = "Search Results"

        recyclerview = view.findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(context!!)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = SearchResultAdapter(this.context!!, mutableListOf())

        launch {
            val fields: HashMap<String, String> = arguments?.getSerializable(KEY_FIELDS) as HashMap<String, String>?
                    ?: HashMap()
            presenter.performSearch(fields)
            recyclerview.addOnScrollListener(InfiniteScrollListener(
                    { page -> fields["page"] = page.toString(); presenter.performSearch(fields)
                    }, layoutManager))
        }

        return view
    }

    override fun showResults(cards: List<Card>) {
        (recyclerview.adapter as SearchResultAdapter).items.addAll(cards.distinctBy { it.name })
        (recyclerview.adapter as SearchResultAdapter).notifyDataSetChanged()
    }

    @Inject
    override fun setSearchResultPresenter(presenter: SearchResultContract.Presenter) {
        this.presenter = presenter
    }
}