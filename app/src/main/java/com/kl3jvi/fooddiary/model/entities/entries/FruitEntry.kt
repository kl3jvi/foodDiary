package com.kl3jvi.fooddiary.model.entities.entries


import com.google.gson.annotations.SerializedName

data class FruitEntry(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("fruitId")
    val fruitId: Int,
    @SerializedName("fruitType")
    val fruitType: String
)