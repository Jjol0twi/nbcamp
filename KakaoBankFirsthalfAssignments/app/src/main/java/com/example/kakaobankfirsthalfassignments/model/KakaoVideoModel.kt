package com.example.kakaobankfirsthalfassignments.model


import com.google.gson.annotations.SerializedName

data class KakaoVideoModel(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Meta(
        @SerializedName("is_end")
        val isEnd: Boolean,
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @SerializedName("total_count")
        val totalCount: Int
    )

    data class Document(
        @SerializedName("author")
        val author: String,
        @SerializedName("datetime")
        val datetime: String,
        @SerializedName("play_time")
        val playTime: Int,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    )
}