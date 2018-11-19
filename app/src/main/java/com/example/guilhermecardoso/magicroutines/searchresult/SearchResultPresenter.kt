package com.example.guilhermecardoso.magicroutines.searchresult

import com.example.guilhermecardoso.magicroutines.service.MagicAPI
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.cancelAndJoin
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException
import javax.inject.Inject

class SearchResultPresenter @Inject constructor(private val service: MagicAPI): SearchResultContract.Presenter {
    lateinit var view: SearchResultContract.View
    var jobs: Job? = null

    override fun performSearch(fields: HashMap<String, String>) {
        jobs = launch(UI) {
            try {
                view.showLoading()
                val response = service.cards(fields).await()
                view.showResults(response.cards)
            } catch (httpEx: HttpException) {
                view.showError()
            } finally {
                view.hideLoading()
            }
        }
    }

    override fun attachView(view: SearchResultContract.View) {
        this.view = view
    }

    override fun dettachView() {
        launch(UI) { jobs?.cancelAndJoin() }
    }
}