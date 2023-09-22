package com.example.kakaobankfirsthalfassignments.network

class KakaoRepository {
    private val builder = KakaoNetwork.getBuilder().create(KakaoService::class.java)
    suspend fun getImageData(
        query: String,
        sortType: String = "accuracy"
    ) =
        builder.requestImage(text = query, sortType = sortType)

    suspend fun getVideoData(
        query: String,
        sortType: String = "accuracy"
    ) = builder.requestVideo(text = query, sortType = sortType)
}