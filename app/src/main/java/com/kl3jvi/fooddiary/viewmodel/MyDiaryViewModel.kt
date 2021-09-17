package com.kl3jvi.fooddiary.viewmodel

import androidx.lifecycle.*
import com.kl3jvi.fooddiary.model.entities.entries.Entries
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.repositories.Repository
import com.kl3jvi.fooddiary.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyDiaryViewModel(private val myDiaryRepository: Repository) : ViewModel() {

    val test = MutableLiveData<Entries>()


    fun getEntries() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = myDiaryRepository.getEntries()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun getFruits() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = myDiaryRepository.getFruits()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun deleteById(entryId: Int) = viewModelScope.launch {
        myDiaryRepository.deleteById(entryId)
    }

    fun deleteAll() = viewModelScope.launch {
        myDiaryRepository.deleteAllEntries()
    }


}

class MyDiaryViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyDiaryViewModel::class.java)) {
            return MyDiaryViewModel(Repository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}