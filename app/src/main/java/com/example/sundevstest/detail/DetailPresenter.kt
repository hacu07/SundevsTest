package com.example.sundevstest.detail

import com.example.sundevstest.detail.events.CreditImageEvent
import com.example.sundevstest.pojo.ComicDetail

interface DetailPresenter {
    fun onCreate()
    fun onDestroy()

    fun getImages(comicDetail: ComicDetail)
    fun onEventCreditImageListener(creditImageEvent : CreditImageEvent)
}