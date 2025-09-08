package com.example.islamicapp.Repository


import com.example.islamicapp.Api.RetrofitInstanceSurah
import com.example.islamicapp.Data.ListOfSurah
import com.example.islamicapp.Data.Surah
import retrofit2.Response

class RepositorySurah {
    suspend fun getSurah(numberOfSurah: Int): Response<Surah> {
        return RetrofitInstanceSurah.api.getSurah(numberOfSurah)
    }
    suspend fun getListOfSurah(): Response<ListOfSurah> {
        return RetrofitInstanceSurah.api.getListOfSurah()
    }
}