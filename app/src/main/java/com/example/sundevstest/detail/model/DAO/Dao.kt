package com.example.sundevstest.detail.model.DAO

import com.example.sundevstest.main.events.ImageEvent
import com.example.sundevstest.pojo.Credit
import com.example.sundevstest.util.APIService
import com.example.sundevstest.util.BasicCallback
import com.example.sundevstest.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Dao {

    fun requestImageCreditComic(credit: Credit, callback: BasicCallback) {
        val SERVICE = Util.getRetrofit(credit.api_detail_url).create(APIService::class.java)

        SERVICE.requestImageCreditComic(Util.PATH_API_KEY).enqueue(object: Callback<ImageEvent> {
            override fun onResponse(call: Call<ImageEvent>, response: Response<ImageEvent>) {
                var event: ImageEvent? = response.body()

                if (event != null){
                    event.typeEvent = if(event.status_code == 1) Util.SUCCESS else Util.ERROR_DATA
                }else{
                    event = ImageEvent(
                        typeEvent = Util.ERROR_RESPONSE,
                        errorMsg = Util.RESPONSE_ERROR_MSG
                    )
                }
                callback.response(event!!)
            }

            override fun onFailure(call: Call<ImageEvent>, t: Throwable) {
                // Request error
                callback.response(
                    ImageEvent(
                        typeEvent = Util.ERROR_CONNECTION,
                        errorMsg = Util.CONNECTION_ERROR_MSG
                    )
                )
            }
        })
    }
}