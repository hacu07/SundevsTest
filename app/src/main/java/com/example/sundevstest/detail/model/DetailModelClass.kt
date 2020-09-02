package com.example.sundevstest.detail.model

import com.example.sundevstest.detail.events.CreditImageEvent
import com.example.sundevstest.detail.model.DAO.Dao
import com.example.sundevstest.main.events.ImageEvent
import com.example.sundevstest.pojo.ComicDetail
import com.example.sundevstest.pojo.Credit
import com.example.sundevstest.util.BasicCallback
import com.example.sundevstest.util.Util
import org.greenrobot.eventbus.EventBus

class DetailModelClass : DetailModel {

    private val mDao: Dao = Dao()

    override fun getImages(comicDetail: ComicDetail) {
        comicDetail.character_credits?.let {characters ->
            for(character in characters!!){
                // Get credit image from API
                mDao.requestImageCreditComic(
                    credit = character,
                    callback = object: BasicCallback{
                        override fun response(event: Any) {
                            val imageEvent: ImageEvent = event as ImageEvent

                            // Response Ok
                            if (imageEvent.typeEvent == Util.SUCCESS && imageEvent.results != null){
                                character.image = imageEvent.results.image

                                postCreditEvent(character,Util.TYPE_CHARACTER)
                            }
                        }
                    })
            }
        }

        comicDetail.team_credits?.let {teams ->
            for(team in teams!!){
                // Get credit image from API
                mDao.requestImageCreditComic(
                    credit = team,
                    callback = object: BasicCallback{
                        override fun response(event: Any) {
                            val imageEvent: ImageEvent = event as ImageEvent

                            // Response Ok
                            if (imageEvent.typeEvent == Util.SUCCESS && imageEvent.results != null){
                                team.image = imageEvent.results.image

                                postCreditEvent(team,Util.TYPE_TEAM)
                            }
                        }
                    })
            }
        }

        comicDetail.location_credits?.let {locations ->
            for(location in locations!!){
                // Get credit image from API
                mDao.requestImageCreditComic(
                    credit = location,
                    callback = object: BasicCallback{
                        override fun response(event: Any) {
                            val imageEvent: ImageEvent = event as ImageEvent

                            // Response Ok
                            if (imageEvent.typeEvent == Util.SUCCESS && imageEvent.results != null){
                                location.image = imageEvent.results.image

                                postCreditEvent(location,Util.TYPE_LOCATION)
                            }
                        }
                    })
            }
        }

        comicDetail.concept_credits?.let {concepts ->
            for(concept in concepts!!){
                // Get credit image from API
                mDao.requestImageCreditComic(
                    credit = concept,
                    callback = object: BasicCallback{
                        override fun response(event: Any) {
                            val imageEvent: ImageEvent = event as ImageEvent

                            // Response Ok
                            if (imageEvent.typeEvent == Util.SUCCESS && imageEvent.results != null){
                                concept.image = imageEvent.results.image

                                postCreditEvent(concept,Util.TYPE_CONCEPT)
                            }
                        }
                    })
            }
        }
    }

    fun postCreditEvent(credit: Credit, typeCharacter: String) {
        EventBus.getDefault().post(
            CreditImageEvent(
                credit,
                typeCharacter
            )
        )
    }


}