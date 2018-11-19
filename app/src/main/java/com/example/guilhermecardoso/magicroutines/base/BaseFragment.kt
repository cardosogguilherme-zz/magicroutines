package com.example.guilhermecardoso.magicroutines.base

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.example.guilhermecardoso.magicroutines.R

abstract class BaseFragment: Fragment(), BaseView {
    lateinit var loading: ProgressBar
    var holderView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    fun inflateFragment(resId: Int, inflater: LayoutInflater): View {

        val inflatedView = inflater.inflate(R.layout.base_fragment, null)
        val holder: ViewStub = inflatedView.findViewById(R.id.holder)
        holder.layoutResource = resId
        holderView = holder.inflate()

        loading = inflatedView.findViewById(R.id.indeterminateBar)
        loading.isIndeterminate = true
        loading.visibility = View.GONE

        return inflatedView
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