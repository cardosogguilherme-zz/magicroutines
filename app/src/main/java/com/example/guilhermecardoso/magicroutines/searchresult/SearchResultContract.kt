package com.example.guilhermecardoso.magicroutines.searchresult

import com.example.guilhermecardoso.magicroutines.base.BasePresenter
import com.example.guilhermecardoso.magicroutines.base.BaseView
import com.example.guilhermecardoso.magicroutines.service.Card

interface SearchResultContract {

    interface Presenter: BasePresenter<View> {
        fun performSearch(fields: HashMap<String, String>)
    }

    interface View: BaseView {
        fun showResults(cards: List<Card>)
    }

}