package com.example.guilhermecardoso.magicroutines.di

import com.example.guilhermecardoso.magicroutines.di.components.DaggerAppComponent
import com.example.guilhermecardoso.magicroutines.di.modules.ContextModule
import com.example.guilhermecardoso.magicroutines.search.SearchContract
import com.example.guilhermecardoso.magicroutines.search.SearchFragment
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultContract
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultFragment

fun SearchResultFragment.buildPresenter(): SearchResultContract.Presenter =
    DaggerAppComponent
            .builder()
            .contextModule(ContextModule(activity?.applicationContext!!))
            .build()
            .buildSearchResultPresenter()