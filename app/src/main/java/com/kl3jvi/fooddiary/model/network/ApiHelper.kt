package com.kl3jvi.fooddiary.model.network

import com.kl3jvi.fooddiary.model.entities.entries.Entries

class ApiHelper(private val apiService: ApiService) {

    suspend fun getEntries() = apiService.getEntries()

    suspend fun getFruits() = apiService.getFruits()

    suspend fun deleteAllEntries() = apiService.deleteAllEntries()

    suspend fun deleteById(entryId: Int) = apiService.deleteById(entryId)

    suspend fun addEntry(entries: Entries) = apiService.addFruitEntry(entries = entries)
}