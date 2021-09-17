package com.kl3jvi.fooddiary.model.entities.local

import android.os.Parcelable
import com.kl3jvi.fooddiary.model.entities.entries.FruitsAddedInThisEntry
import kotlinx.parcelize.Parcelize

@Parcelize
data class EntryTransfer(
    val fruitsAdded: List<FruitsAddedInThisEntry>,
    val id: Int
) : Parcelable
