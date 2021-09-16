package com.kl3jvi.fooddiary.model.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Fruit(
    @Json(name = "amount")
    val amount: Int,
    @Json(name = "fruitId")
    val fruitId: Int,
    @Json(name = "fruitType")
    val fruitType: String
)