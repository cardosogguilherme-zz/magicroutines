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
        //?.add(R.id.fragment_container,SearchResultFragment(), "Result")?.addToBackStack(null)?.commit()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.let {
            it.addToBackStack(this.javaClass.canonicalName)
            it.replace(R.id.fragment_container,SearchResultFragment(), SearchResultFragment::javaClass.name)
            it.commit()
        }
    }
}