package com.kl3jvi.fooddiary.model.entities.entries


import com.google.gson.annotations.SerializedName

data class EntriesItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("fruit")
    val fruit: List<FruitEntry>,
    @SerializedName("id")
    val id: Int
)