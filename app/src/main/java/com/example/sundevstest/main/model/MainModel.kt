package com.example.sundevstest.main.model

import com.example.sundevstest.pojo.Credit
import com.example.sundevstest.pojo.Result

interface MainModel {
    fun getListOfComics()
    fun requestDetailComic(result:Result)
}