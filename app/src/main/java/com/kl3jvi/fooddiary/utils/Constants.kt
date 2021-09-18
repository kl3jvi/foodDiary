package com.kl3jvi.fooddiary.utils

import android.content.res.ColorStateList
import android.graphics.Color

object Constants {
    const val EXTRA_DETAILS = "ExtraDetails"
    const val BASE_URL = "https://fruitdiary.test.themobilelife.com/"


    fun getChipColor(fruitType: String): ColorStateList {

        when (fruitType) {
            "apple" -> {
                return ColorStateList.valueOf(Color.argb(255, 102, 180, 71))
            }
            "banana" -> {
                return ColorStateList.valueOf(Color.argb(255, 255, 225, 53))
            }
            "cherry" -> {
                return ColorStateList.valueOf(Color.argb(255, 210, 4, 45))
            }
            "lemon" -> {
                return ColorStateList.valueOf(Color.argb(255, 255, 207, 0))
            }
            "orange" -> {
                return ColorStateList.valueOf(Color.argb(255, 255, 165, 0))
            }
            "pear" -> {
                return ColorStateList.valueOf(Color.argb(255, 209, 226, 49))
            }
            "pineapple" -> {
                return ColorStateList.valueOf(Color.argb(255, 86, 60, 13))
            }
        }
        return ColorStateList.valueOf(Color.argb(255, 0, 144, 193))
    }
}
