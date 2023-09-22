package com.example.kakaobankfirsthalfassignments.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResultModel(
    val previewImg: String,
    val title: String,
    val postTime: String,
    val isStorage: Boolean = false,
) : Parcelable
//{
//    fun changeTimeFormat(): String {
//        val outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        val formattedPostTime = this.postTime.format(outputFormat)
//        return copy(postTime = formattedPostTime).postTime
//    }
//}