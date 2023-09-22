package com.example.kakaobankfirsthalfassignments.network

import com.example.kakaobankfirsthalfassignments.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object KakaoNetwork {
    private const val Kakao_BASE_URL = "https://dapi.kakao.com/"

    private fun createClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) interceptor.level = HttpLoggingInterceptor.Level.BODY
        else interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build()
    }

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(Kakao_BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getBuilder(): Retrofit = retrofitBuilder

//    val apiService: KakaoService = retrofitBuilder.create(KakaoService::class.java)
}