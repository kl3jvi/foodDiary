package com.kl3jvi.fooddiary.model.repositories

import com.kl3jvi.fooddiary.model.entities.entries.CreateEntry
import com.kl3jvi.fooddiary.model.network.ApiHelper

class Repository(private val apiHelper: ApiHelper) {
    suspend fun getEntries() = apiHelper.getEntries()

    suspend fun getFruits() = apiHelper.getFruits()

    suspend fun deleteAllEntries() = apiHelper.deleteAllEntries()

    suspend fun deleteById(entryId: Int) = apiHelper.deleteById(entryId = entryId)

    suspend fun addEntry(createEntry: CreateEntry) = apiHelper.addEntry(createEntry = createEntry)

    suspend fun setEditEntry(entryId: Int, fruitId: Int, nrOfFruit: Int) =
        apiHelper.setEditEntry(entryId, fruitId, nrOfFruit)

}