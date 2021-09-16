package com.kl3jvi.fooddiary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.kl3jvi.fooddiary.model.network.ApiHelper
import com.kl3jvi.fooddiary.model.repositories.MyDiaryRepository
import com.kl3jvi.fooddiary.utils.Resource
import kotlinx.coroutines.Dispatchers

class MyDiaryViewModel(private val myDiaryRepository: MyDiaryRepository) : ViewModel() {

    val test = MutableLiveData<String>()


    fun doTest(){
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

}

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyDiaryViewModel::class.java)) {
            return MyDiaryViewModel(MyDiaryRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}