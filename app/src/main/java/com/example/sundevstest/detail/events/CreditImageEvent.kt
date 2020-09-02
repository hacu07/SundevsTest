package com.example.sundevstest.detail.events

import com.example.sundevstest.pojo.Credit
import java.io.Serializable

data class CreditImageEvent(
    val credit: Credit,
    val typeCredit: String
): Serializable