package com.kl3jvi.fooddiary.model.network

class ApiHelper(private val apiService: ApiService) {

    suspend fun getEntries() = apiService.getEntries()
}