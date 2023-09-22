package com.example.kakaobankfirsthalfassignments.network

import com.example.kakaobankfirsthalfassignments.BuildConfig
import com.example.kakaobankfirsthalfassignments.model.KakaoImageModel
import com.example.kakaobankfirsthalfassignments.model.KakaoVideoModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoService {

    @Headers("Authorization: ${BuildConfig.kakao_key_restapi}")
    @GET("v2/search/image")
    suspend fun requestImage(
        @Query("query") text: String,
        @Query("sort") sortType: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80,
    ): KakaoImageModel

    @GET("v2/search/vclip")
    suspend fun requestVideo(
        @Query("query") text: String,
        @Query("sort") sortType: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80,
    ): KakaoVideoModel

//    suspend fun requestImage(
//        @Header("Authorization") apiKey: String = BuildConfig.kakao_key_restapi,
//        @Query("query") text: String,
//        @Query("sort") sortType: String = "accuracy",
//        @Query("page") page: Int = 1,
//        @Query("size") size: Int = 80,
//    ): KakaoImageModel
//    fun requestImage(
//        @Query("query") text: String,
//        @Query("sort") sortType: String = "accuracy",
//        @Query("page") page: Int = 1,
//        @Query("size") size: Int = 80,
//    ): Call<KakaoImageSearch>

}