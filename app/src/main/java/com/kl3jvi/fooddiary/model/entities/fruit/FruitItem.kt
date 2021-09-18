package com.kl3jvi.fooddiary.model.entities.fruit


import com.google.gson.annotations.SerializedName

data class FruitItem(
    @SerializedName("id")
    val fruitId: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("vitamins")
    val vitamins: Int
)