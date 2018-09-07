package com.example.guilhermecardoso.magicroutines.base

interface BasePresenter<in V: BaseView> {
    fun attachView(view: V)
    fun dettachView()
}