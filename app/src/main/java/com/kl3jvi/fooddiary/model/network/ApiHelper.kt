package com.kl3jvi.fooddiary.model.network

import com.kl3jvi.fooddiary.model.entities.entries.CreateEntry

class ApiHelper(private val apiService: ApiService) {

    suspend fun getEntries() = apiService.getEntries()

    suspend fun getFruits() = apiService.getFruits()

    suspend fun deleteAllEntries() = apiService.deleteAllEntries()

    suspend fun deleteById(entryId: Int) = apiService.deleteById(entryId)

    suspend fun addEntry(createEntry: CreateEntry) =
        apiService.addFruitEntry(createEntry = createEntry)

    suspend fun setEditEntry(entryId: Int, fruitId: Int, nrOfFruit: Int) =
        apiService.setEditEntry(entryId, fruitId, nrOfFruit)
}