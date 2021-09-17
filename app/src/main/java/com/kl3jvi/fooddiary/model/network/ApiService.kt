package com.kl3jvi.fooddiary.model.network

import com.kl3jvi.fooddiary.model.entities.Entries
import com.kl3jvi.fooddiary.model.entities.Fruit
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {


    @GET("api/entries")
    suspend fun getEntries(): Entries

    @GET("api/fruit")
    suspend fun getFruits(): Fruit

    @DELETE("api/entries")
    suspend fun deleteAllEntries(): Response<ResponseBody>

    @DELETE("api/entry/{entryId}")
    suspend fun deleteById(@Path("entryId") entryId: Int):Response<ResponseBody>


}