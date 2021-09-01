package com.example.papeldoviz.servis

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyListChartList(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("timestamps")
    val timestamps: List<String>?,
    @SerializedName("prices")
    val prices: List<String>?
) : Parcelable
