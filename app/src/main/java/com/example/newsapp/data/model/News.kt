package com.example.newsapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class News(
    val description: String? = null,
    val author: String? = null,
    val url: String? = null,
    val title: String? = null,
    val urlToImage: String? = null,
) : Parcelable