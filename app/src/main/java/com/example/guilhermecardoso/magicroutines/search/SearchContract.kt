package com.example.guilhermecardoso.magicroutines.search

import com.example.guilhermecardoso.magicroutines.base.BasePresenter
import com.example.guilhermecardoso.magicroutines.base.BaseView

interface SearchContract {

    interface View: BaseView {
        fun showSearchError()
        fun searchPerformed()
    }

    interface Presenter: BasePresenter<View> {

    }

}