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


class SharedViewModel(private val sharedRepo: Repository) : ViewModel() {


    fun addEntry(createEntry: CreateEntry) {
        viewModelScope.launch {
            sharedRepo.addEntry(createEntry).message()
        }
    }


    fun getEntries() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = sharedRepo.getEntries()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun getFruits() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = sharedRepo.getFruits()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun deleteById(entryId: Int) = viewModelScope.launch {
        sharedRepo.deleteById(entryId)
    }

    fun deleteAll() = viewModelScope.launch {
        sharedRepo.deleteAllEntries()
    }


}

class SharedViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}