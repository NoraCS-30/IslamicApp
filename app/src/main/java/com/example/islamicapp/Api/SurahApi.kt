package com.example.islamicapp.Api


import com.example.islamicapp.Data.ListOfSurah
import com.example.islamicapp.Data.Surah
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SurahApi {
    @GET("surah/{number}")
    suspend fun getSurah(
        @Path("number") numberOfSurah: Int
    ): Response<Surah>

    @GET("surah")
    suspend fun getListOfSurah(): Response<ListOfSurah>
}