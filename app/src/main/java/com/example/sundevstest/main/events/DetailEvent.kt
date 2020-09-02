package com.example.sundevstest.main.events

import com.example.sundevstest.pojo.ComicDetail
import java.io.Serializable

data class DetailEvent(
    var typeEvent: Int,
    var errorMsg: String,
    val error: String? = null,
    val limit: Int = 0,
    val offset: Int = 0,
    val number_of_page_results: Int = 0,
    val status_code : Int = 0,
    val results: ComicDetail? = null
): Serializable