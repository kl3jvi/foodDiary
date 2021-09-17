package com.kl3jvi.fooddiary.viewmodel

import androidx.lifecycle.*
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.repositories.MyDiaryRepository
import com.kl3jvi.fooddiary.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyDiaryViewModel(private val myDiaryRepository: MyDiaryRepository) : ViewModel() {

    val test = MutableLiveData<String>()


    fun doTest() {
        test.value = "ckemi"
    }

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


}

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyDiaryViewModel::class.java)) {
            return MyDiaryViewModel(MyDiaryRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}