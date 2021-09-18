package com.kl3jvi.fooddiary.model.network

import com.kl3jvi.fooddiary.model.entities.entries.CreateEntry
import com.kl3jvi.fooddiary.model.entities.entries.Entries
import com.kl3jvi.fooddiary.model.entities.fruit.Fruit
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {


    @GET("api/entries")
    suspend fun getEntries(): Entries

    @GET("api/fruit")
    suspend fun getFruits(): Fruit

    @DELETE("api/entries")
    suspend fun deleteAllEntries(): Response<ResponseBody>

    @DELETE("api/entry/{entryId}")
    suspend fun deleteById(@Path("entryId") entryId: Int): Response<ResponseBody>

    @POST("api/entries")
    suspend fun addFruitEntry(@Body createEntry: CreateEntry): Response<ResponseBody>


    @POST("api/entry/{entryId}/fruit/{fruitId}?")
    suspend fun setEditEntry(
        @Path("entryId") entryId: Int,
        @Path("fruitId") fruitId: Int,
        @Query("amount") nrOfFruit: Int
    ): Response<ResponseBody>
}