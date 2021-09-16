package com.kl3jvi.fooddiary.model.network

import com.kl3jvi.fooddiary.model.entities.Entries
import com.kl3jvi.fooddiary.model.entities.Fruit
import retrofit2.http.GET


interface ApiService {


    @GET("api/entries")
    suspend fun getEntries(): Entries

    @GET("api/fruit")
    suspend fun getFruits(): Fruit

}