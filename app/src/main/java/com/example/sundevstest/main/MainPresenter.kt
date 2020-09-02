package com.example.sundevstest.main

import com.example.sundevstest.main.events.DetailEvent
import com.example.sundevstest.main.events.ResultEvent
import com.example.sundevstest.pojo.Result

interface MainPresenter {
    fun onCreate()
    fun onDestroy()

    fun getResults(): ArrayList<Result>
    fun requestListOfComics()
    fun onResultEventListener(resultEvent: ResultEvent)

    fun requestDetailComic(result: Result)
    fun onDetailEventListener(detailEvent: DetailEvent)
}