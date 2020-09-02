package com.example.sundevstest.main.model

import com.example.sundevstest.main.events.DetailEvent
import com.example.sundevstest.main.events.ImageEvent
import com.example.sundevstest.main.events.ResultEvent
import com.example.sundevstest.main.model.dao.DAO
import com.example.sundevstest.pojo.Credit
import com.example.sundevstest.pojo.Result
import com.example.sundevstest.util.BasicCallback
import com.example.sundevstest.util.Util
import org.greenrobot.eventbus.EventBus

class MainModelClass : MainModel {

    private val mDao = DAO()

    override fun getListOfComics() {
        mDao.getListOfComics(object: BasicCallback{
            override fun response(event: Any) {
                postListOfComics(event as ResultEvent)
            }
        })
    }

    fun postListOfComics(resultEvent: ResultEvent) {
        EventBus.getDefault().post(resultEvent)
    }

    override fun requestDetailComic(result: Result) {
        mDao.requestDetailComic(result, object: BasicCallback{
            override fun response(event: Any) {
                postDetailComic(event as DetailEvent)
            }
        })
    }

    fun postDetailComic(detailEvent: DetailEvent) {
        EventBus.getDefault().post(detailEvent)
    }
}