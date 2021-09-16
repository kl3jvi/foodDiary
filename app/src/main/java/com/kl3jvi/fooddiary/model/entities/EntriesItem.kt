package com.kl3jvi.fooddiary.model.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EntriesItem(
    @Json(name = "date")
    val date: String,
    @Json(name = "fruit")
    val fruit: List<Fruit>,
    @Json(name = "id")
    val id: Int
)