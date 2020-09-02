package com.example.sundevstest.util

import android.content.Context
import android.widget.Toast
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Util {

    companion object{
        val TYPE_CHARACTER: String = "CHARACTER"
        val TYPE_LOCATION: String = "LOCATION"
        val TYPE_CONCEPT: String = "CONCEPT"
        val TYPE_TEAM: String = "TEAM"
        val RESPONSE_ERROR_MSG: String = "NOT RESPONSE"
        val CONNECTION_ERROR_MSG: String = "NOT CONNECTION."
        val SUCCESS = 0
        val ERROR_DATA = 100
        val ERROR_RESPONSE = 101
        val ERROR_CONNECTION = 102

        val API_KEY = "1fe1b3fe1bffee3d0335abc616c5b17ba2979f58"
        val BASE_URL_ISSUES = "https://comicvine.gamespot.com/api/"

        val PATH_API_KEY = "?api_key="+Util.API_KEY+"&format=json"

        val LIST = 1
        val GRID = 2

        var visualization = LIST

        fun getRetrofit(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun showToast(context: Context, msg: String){
            Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
        }
    }
}