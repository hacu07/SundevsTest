package com.example.sundevstest.detail.view

import com.example.sundevstest.pojo.Credit

interface DetailView {
    fun loadCreditImageCharacter(credit: Credit)
    fun loadCreditImageTeam(credit: Credit)
    fun loadCreditImageLocation(credit: Credit)
    fun loadCreditImageConcept(credit: Credit)
}