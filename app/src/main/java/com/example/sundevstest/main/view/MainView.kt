package com.example.sundevstest.main.view

import com.example.sundevstest.pojo.ComicDetail
import com.example.sundevstest.pojo.Result

interface MainView {
    fun showProgressBar(show: Boolean)
    fun showProgresBarComic(show: Boolean)
    fun showErrorMsg(msg: String)
    fun showToast(msg: String)
    fun loadComics(results: ArrayList<Result>)
    fun enableElementsUI(enable: Boolean)
    fun loadDetailResult(results: ComicDetail?)
}