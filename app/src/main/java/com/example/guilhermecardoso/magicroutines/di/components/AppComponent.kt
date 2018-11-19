package com.example.guilhermecardoso.magicroutines.di.components

import com.example.guilhermecardoso.magicroutines.di.modules.MagicServiceModule
import com.example.guilhermecardoso.magicroutines.di.modules.RetrofitModule
import com.example.guilhermecardoso.magicroutines.di.modules.SearchResultModule
import com.example.guilhermecardoso.magicroutines.search.SearchContract
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultContract
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultFragment
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultPresenter
import dagger.Component

@Component(modules = [RetrofitModule::class, MagicServiceModule::class, SearchResultModule::class])
interface AppComponent {

    fun buildSearchResultPresenter(): SearchResultPresenter

    fun inject(fragment: SearchResultFragment)
}