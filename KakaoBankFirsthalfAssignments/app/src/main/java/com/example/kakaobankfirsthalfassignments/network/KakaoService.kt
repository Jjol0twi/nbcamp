package com.example.kakaobankfirsthalfassignments.network

import com.example.kakaobankfirsthalfassignments.BuildConfig
import com.example.kakaobankfirsthalfassignments.model.KakaoImageSearch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @Headers("Authorization: ${BuildConfig.kakao_key_restapi}")
    @GET("v2/search/image")
    suspend fun searchKakao(
        @Query("query") text: String,
        @Query("sort") sortType: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80,
    ): KakaoImageSearch
//    suspend fun searchKakao(
//        @Header("Authorization") apiKey : String = BuildConfig.kakao_key_restapi,
//        @Query("query") text : String,
//        @Query("sort") sortType : String = "accuracy",
//        @Query("page") page : Int = 1,
//        @Query("size") size : Int = 80,
//    ): KakaoImageSearch

}