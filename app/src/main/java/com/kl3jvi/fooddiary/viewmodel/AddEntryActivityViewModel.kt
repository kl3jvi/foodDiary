package com.kl3jvi.fooddiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.kl3jvi.fooddiary.model.entities.entries.CreateEntry
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.repositories.Repository
import com.kl3jvi.fooddiary.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEntryActivityViewModel(private val addEntryRepository: Repository) : ViewModel() {




    fun getFruits() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = addEntryRepository.getFruits()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun setEditEntry(entryId: Int, fruitId: Int, nrOfFruit: Int) {
        viewModelScope.launch {
            addEntryRepository.setEditEntry(entryId, fruitId, nrOfFruit)
        }
    }


    fun getEntries() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = addEntryRepository.getEntries()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}


class AddEntryViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEntryActivityViewModel::class.java)) {
            return AddEntryActivityViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}