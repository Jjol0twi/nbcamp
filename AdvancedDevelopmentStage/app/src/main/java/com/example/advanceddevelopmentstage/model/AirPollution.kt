package com.example.advanceddevelopmentstage.model

data class AirPollution(
    val response: Response
)

data class Response(
    val body: Body,
    val header: Header
)

data class Header(
    val resultCode: String,
    val resultMsg: String
)

data class Body(
    val items: List<Item>,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class Item(
    val coFlag: Any,
    val coGrade: String,
    val coValue: String,
    val dataTime: String,
    val khaiGrade: String,
    val khaiValue: String,
    val no2Flag: Any,
    val no2Grade: String,
    val no2Value: String,
    val o3Flag: Any,
    val o3Grade: String,
    val o3Value: String,
    val pm10Flag: Any,
    val pm10Grade: String,
    val pm10Value: String,
    val pm25Flag: Any,
    val pm25Grade: String,
    val pm25Value: String,
    val sidoName: String,
    val so2Flag: Any,
    val so2Grade: String,
    val so2Value: String,
    val stationName: String
)