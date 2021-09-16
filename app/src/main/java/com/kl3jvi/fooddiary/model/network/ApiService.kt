package com.kl3jvi.fooddiary.model.network

import com.kl3jvi.fooddiary.model.entities.Entries
import retrofit2.http.GET


interface ApiService {

    @GET("users") //TODO CHange the entry
    suspend fun getUsers(): List<Entries>

}