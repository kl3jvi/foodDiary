package com.kl3jvi.fooddiary.model.network

import com.kl3jvi.fooddiary.model.entities.Entries
import retrofit2.http.GET


interface ApiService {


    @GET("/api/fruit")
    suspend fun getEntries(): List<Entries>

}