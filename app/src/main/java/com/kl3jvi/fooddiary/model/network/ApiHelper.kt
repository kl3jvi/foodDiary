package com.kl3jvi.fooddiary.model.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getEntries() = apiService.getEntries()

    suspend fun getFruits() = apiService.getFruits()

    suspend fun deleteAllEntries() = apiService.deleteAllEntries()

    suspend fun deleteById(entryId: Int) = apiService.deleteById(entryId)
}