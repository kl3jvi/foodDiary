package com.kl3jvi.fooddiary.model.entities.entries


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FruitsAddedInThisEntry(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("fruitId")
    val fruitId: Int,
    @SerializedName("fruitType")
    val fruitType: String
) : Parcelable