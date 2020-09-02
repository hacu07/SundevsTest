package com.example.sundevstest.util

import com.example.sundevstest.main.events.DetailEvent
import com.example.sundevstest.main.events.ImageEvent
import com.example.sundevstest.main.events.ResultEvent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("issues/{path}")
    fun getListOfComics(@Path("path") path: String):Call<ResultEvent>

    @GET("{path}")
    fun requestDetailComic(@Path("path") path: String):Call<DetailEvent>

    @GET("{path}")
    fun requestImageCreditComic(@Path("path") path: String):Call<ImageEvent>
}