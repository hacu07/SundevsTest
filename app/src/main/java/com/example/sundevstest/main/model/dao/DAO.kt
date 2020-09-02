package com.example.sundevstest.main.model.dao

import com.example.sundevstest.main.events.DetailEvent
import com.example.sundevstest.main.events.ImageEvent
import com.example.sundevstest.main.events.ResultEvent
import com.example.sundevstest.pojo.Credit
import com.example.sundevstest.pojo.Result
import com.example.sundevstest.util.APIService
import com.example.sundevstest.util.BasicCallback
import com.example.sundevstest.util.Util
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DAO {

    fun getListOfComics(callback: BasicCallback){
        val SERVICE = Util.getRetrofit(Util.BASE_URL_ISSUES).create(APIService::class.java)

        SERVICE.getListOfComics(Util.PATH_API_KEY).enqueue(object: Callback<ResultEvent>{
            override fun onResponse(call: Call<ResultEvent>, response: Response<ResultEvent>) {
                var event: ResultEvent? = response.body()

                if (event != null){
                    event.typeEvent = if(event.status_code == 1) Util.SUCCESS else Util.ERROR_DATA
                }else{
                    event = ResultEvent(
                        typeEvent = Util.ERROR_RESPONSE,
                        errorMsg = Util.RESPONSE_ERROR_MSG
                    )
                }
                callback.response(event!!)
            }

            override fun onFailure(call: Call<ResultEvent>, t: Throwable) {
                // Request error
                callback.response(
                    ResultEvent(
                        typeEvent = Util.ERROR_CONNECTION,
                        errorMsg = Util.CONNECTION_ERROR_MSG
                    )
                )
            }
        })
    }

    fun requestDetailComic(result:Result, callback: BasicCallback){
        val SERVICE = Util.getRetrofit(result.api_detail_url).create(APIService::class.java)

        SERVICE.requestDetailComic(Util.PATH_API_KEY).enqueue(object: Callback<DetailEvent>{
            override fun onResponse(call: Call<DetailEvent>, response: Response<DetailEvent>) {
                var event: DetailEvent? = response.body()

                if (event != null){
                    event.typeEvent = if(event.status_code == 1) Util.SUCCESS else Util.ERROR_DATA
                }else{
                    event = DetailEvent(
                        typeEvent = Util.ERROR_RESPONSE,
                        errorMsg = Util.RESPONSE_ERROR_MSG
                    )
                }
                callback.response(event!!)
            }

            override fun onFailure(call: Call<DetailEvent>, t: Throwable) {
                // Request error
                callback.response(
                    DetailEvent(
                        typeEvent = Util.ERROR_CONNECTION,
                        errorMsg = Util.CONNECTION_ERROR_MSG
                    )
                )
            }
        })
    }
}