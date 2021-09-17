package com.kl3jvi.fooddiary.model.entities.entries


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EntriesItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("fruit")
    val fruit: List<FruitEntry>,
    @SerializedName("id")
    val id: Int
) : Parcelable