package com.kl3jvi.fooddiary.model.entities.entries


import com.google.gson.annotations.SerializedName

data class CreateEntry(
    @SerializedName("date")
    val date: String
)