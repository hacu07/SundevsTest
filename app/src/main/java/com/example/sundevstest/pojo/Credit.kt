package com.example.sundevstest.pojo

import java.io.Serializable

data class Credit(
    val api_detail_url: String,
    val id: Int,
    val name: String,
    val site_detail_url: String,
    var image: Image
): Serializable