package com.kl3jvi.fooddiary.model.repositories

import com.kl3jvi.fooddiary.model.entities.entries.Entries
import com.kl3jvi.fooddiary.model.network.ApiHelper

class AddEntryRepository(private val apiHelper: ApiHelper) {
    suspend fun addEntry(entries: Entries) = apiHelper.addEntry(entries = entries)
}