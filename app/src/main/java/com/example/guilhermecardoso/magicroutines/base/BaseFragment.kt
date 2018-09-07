package com.example.guilhermecardoso.magicroutines.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.guilhermecardoso.magicroutines.R

open class BaseFragment: Fragment(), BaseView {
    lateinit var loading: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.base_fragment, container, false)

        loading = view.findViewById(R.id.indeterminateBar)
        loading.visibility = View.GONE

        return view
    }

    override fun showError() {
        view?.let { Snackbar.make(it,
                "An error ocurred", Snackbar.LENGTH_SHORT).show() }
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }
}