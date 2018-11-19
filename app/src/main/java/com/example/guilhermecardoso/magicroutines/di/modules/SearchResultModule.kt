package com.example.guilhermecardoso.magicroutines.di.modules

import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultContract
import com.example.guilhermecardoso.magicroutines.searchresult.SearchResultPresenter
import com.example.guilhermecardoso.magicroutines.service.MagicAPI
import dagger.Module
import dagger.Provides

@Module
class SearchResultModule {

    @Provides
    fun providesSearchResultPresenter(service: MagicAPI): SearchResultContract.Presenter =
            SearchResultPresenter(service)
}

