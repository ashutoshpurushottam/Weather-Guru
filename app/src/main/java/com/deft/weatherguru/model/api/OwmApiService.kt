package com.deft.weatherguru.model.api

import com.deft.weatherguru.BuildConfig
import com.deft.weatherguru.model.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Current: https://api.openweathermap.org/data/2.5/weather?q=london&appid=c648acd3649fb8a0ce09c3ff9a096d4a

const val BASE_OWM_URL = "https://api.openweathermap.org/data/2.5/"

interface OwmApiService {
    @GET("weather")
    suspend fun getCurrentWeather(
      @Query("q") location: String,
      @Query("lang") languageCode: String = "en"
    ): CurrentWeatherResponse

    companion object {
        operator fun invoke(): OwmApiService {
            val requestInterceptor = Interceptor {chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid", BuildConfig.OWM_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor).build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_OWM_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OwmApiService::class.java)
        }
    }
}