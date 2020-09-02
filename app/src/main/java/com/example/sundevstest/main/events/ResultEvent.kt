package com.example.sundevstest.main.events

import com.example.sundevstest.pojo.Result
import java.io.Serializable

data class ResultEvent(
    var typeEvent: Int,
    var errorMsg: String,
    val error: String? = null,
    val limit: Int = 0,
    val offset: Int = 0,
    val number_of_page_results: Int = 0,
    val status_code : Int = 0,
    val results: ArrayList<Result>? = null
) : Serializable