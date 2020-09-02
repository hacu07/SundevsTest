package com.example.sundevstest.pojo

import com.example.sundevstest.pojo.Credit
import com.example.sundevstest.pojo.Image
import java.io.Serializable

data class ComicDetail(
    val image: Image,
    var character_credits: ArrayList<Credit>? = null,
    var location_credits: ArrayList<Credit>? = null,
    var team_credits: ArrayList<Credit>? = null,
    var concept_credits: ArrayList<Credit>? = null
): Serializable