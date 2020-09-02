package com.example.sundevstest.detail

import com.example.sundevstest.detail.events.CreditImageEvent
import com.example.sundevstest.detail.model.DetailModel
import com.example.sundevstest.detail.model.DetailModelClass
import com.example.sundevstest.detail.view.DetailActivity
import com.example.sundevstest.detail.view.DetailView
import com.example.sundevstest.pojo.ComicDetail
import com.example.sundevstest.util.Util
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class DetailPresenterClass : DetailPresenter {

    private var mView: DetailView? = null
    private val mModel: DetailModel = DetailModelClass()

    constructor(mView: DetailActivity){
        this.mView = mView
    }

    /*******************************+
     * DetailPresenter
     */
    override fun onCreate() {
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mView =  null
    }

    override fun getImages(comicDetail: ComicDetail) {
        mView.let {
            mModel.getImages(comicDetail)
        }
    }

    @Subscribe
    override fun onEventCreditImageListener(creditImageEvent: CreditImageEvent) {
        if (mView != null){
            when(creditImageEvent.typeCredit){
                Util.TYPE_CHARACTER -> mView?.loadCreditImageCharacter(creditImageEvent.credit)
                Util.TYPE_TEAM -> mView?.loadCreditImageTeam(creditImageEvent.credit)
                Util.TYPE_LOCATION -> mView?.loadCreditImageLocation(creditImageEvent.credit)
                Util.TYPE_CONCEPT -> mView?.loadCreditImageConcept(creditImageEvent.credit)
            }
        }
    }
}