package com.example.applemarket.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductItem(
    val image: Int,
    val name: String,
    val description: String,
    val seller: String,
    val price: String,
    val address: String,
    val likeCount: String,
    val chatCount: String,
) : Parcelable
