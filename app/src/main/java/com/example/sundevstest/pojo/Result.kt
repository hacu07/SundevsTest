package com.example.sundevstest.pojo

import java.io.Serializable

data class Result(
    val date_added : String,
    val name: String,
    val issue_number: String,
    val image: Image,
    val api_detail_url: String
): Serializable