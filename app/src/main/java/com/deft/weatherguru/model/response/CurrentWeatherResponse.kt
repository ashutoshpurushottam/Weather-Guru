package com.deft.weatherguru.model.response

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    val cod: Int,
    val clouds: Clouds,
    @SerializedName("main")
    val mainWeatherInfo: MainWeatherInfo,
    val dt: Int,
    val name: String,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)