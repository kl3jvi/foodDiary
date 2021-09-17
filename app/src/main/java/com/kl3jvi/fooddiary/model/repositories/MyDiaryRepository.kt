package com.kl3jvi.fooddiary.model.repositories

import com.kl3jvi.fooddiary.model.network.ApiHelper
import java.util.concurrent.Flow

class MyDiaryRepository(private val apiHelper: ApiHelper) {
    suspend fun getEntries() = apiHelper.getEntries()

    suspend fun getFruits() = apiHelper.getFruits()

    suspend fun deleteAllEntries() = apiHelper.deleteAllEntries()

    suspend fun deleteById(entryId: Int) = apiHelper.deleteById(entryId)

}