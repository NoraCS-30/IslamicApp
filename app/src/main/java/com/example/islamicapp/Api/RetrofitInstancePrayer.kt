package com.example.islamicapp.Api

import com.example.islamicapp.util.Constansts.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstancePrayer {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api: PrayerApi by lazy {
        retrofit.create(PrayerApi::class.java)
    }


}
