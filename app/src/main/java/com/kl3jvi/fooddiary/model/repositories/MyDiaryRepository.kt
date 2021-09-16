package com.kl3jvi.fooddiary.model.repositories

import com.kl3jvi.fooddiary.model.network.ApiHelper

class MyDiaryRepository(private val apiHelper: ApiHelper) {
    suspend fun getEntries() = apiHelper.getEntries()
}