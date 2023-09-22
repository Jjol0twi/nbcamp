package com.example.kakaobankfirsthalfassignments.model


import com.google.gson.annotations.SerializedName

data class KakaoImageModel(
    @SerializedName("documents")
    val documents: ArrayList<Document>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Document(
        @SerializedName("collection")
        val collection: String,
        @SerializedName("datetime")
        val datetime: String,
        @SerializedName("display_sitename")
        val siteName: String,
        @SerializedName("doc_url")
        val docUrl: String,
        @SerializedName("height")
        val height: Int,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,
        @SerializedName("width")
        val width: Int
    )

    data class Meta(
        @SerializedName("is_end")
        val isEnd: Boolean,
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @SerializedName("total_count")
        val totalCount: Int
    )
}