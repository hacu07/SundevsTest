package com.example.sundevstest.main

import com.example.sundevstest.main.events.DetailEvent
import com.example.sundevstest.main.events.ResultEvent
import com.example.sundevstest.main.model.MainModel
import com.example.sundevstest.main.model.MainModelClass
import com.example.sundevstest.main.view.MainActivity
import com.example.sundevstest.main.view.MainView
import com.example.sundevstest.pojo.Result
import com.example.sundevstest.util.Util
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainPresenterClass : MainPresenter {

    private var mView: MainView? = null
    private val mModel: MainModel = MainModelClass()
    private lateinit var results: ArrayList<Result>

    constructor(mView: MainActivity){
        this.mView = mView
    }

    /******************************
     * MainPresenter Interface
     */
    override fun onCreate() {
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mView = null
    }

    override fun requestListOfComics() {
        mView.let {
            it?.showProgressBar(true)
            it?.enableElementsUI(false)
            mModel.getListOfComics()
        }
    }

    override fun getResults(): ArrayList<Result> {
        return this.results
    }

    @Subscribe
    override fun onResultEventListener(resultEvent: ResultEvent) {
        if (mView != null){
            mView?.showProgressBar(false)

            when(resultEvent.typeEvent){
                Util.SUCCESS -> {
                    this.results = resultEvent.results!!
                    mView?.enableElementsUI(true)
                    mView?.loadComics(resultEvent.results!!)
                }
                else -> {
                    mView?.showErrorMsg(resultEvent.errorMsg)
                }
            }
        }
    }


    override fun requestDetailComic(result: Result) {
        mView?.let {
            it.showProgresBarComic(true)
            it.enableElementsUI(false)
            mModel.requestDetailComic(result)
        }
    }

    @Subscribe
    override fun onDetailEventListener(detailEvent: DetailEvent) {
        if (mView != null){
            mView?.showProgresBarComic(false)
            when(detailEvent.typeEvent){
                Util.SUCCESS -> {
                    mView?.enableElementsUI(true)
                    mView?.loadDetailResult(detailEvent.results)
                }
                else -> {
                    mView?.showErrorMsg(detailEvent.errorMsg)
                }
            }
        }
    }
}