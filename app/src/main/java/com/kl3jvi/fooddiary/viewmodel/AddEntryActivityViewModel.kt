package com.kl3jvi.fooddiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.repositories.AddEntryRepository

class AddEntryActivityViewModel(private val addEntryRepository: AddEntryRepository) : ViewModel() {

}


class AddEntryViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEntryActivityViewModel::class.java)) {
            return AddEntryActivityViewModel(AddEntryRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}