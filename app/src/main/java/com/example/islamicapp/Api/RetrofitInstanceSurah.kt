package com.example.islamicapp.Api



import com.example.islamicapp.util.Constansts.Companion.BASE_URL_SURAH
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceSurah {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SURAH)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api : SurahApi by lazy {
        retrofit.create(SurahApi::class.java)
    }
}