package com.example.islamicapp.Repository

import com.example.islamicapp.Api.RetrofitInstancePrayer
import com.example.islamicapp.Data.Prayer
import retrofit2.Response


class RepositoryPrayer {
   suspend fun getPrayerTimes(Date:String,lat:Double , lon:Double): Response<Prayer> {
        return RetrofitInstancePrayer.api.getPrayerTimes(Date,lat,lon)
    }
    suspend fun getPrayerTimesMakkah(Date:String,lat:Double , lon:Double): Response<Prayer> {
        return RetrofitInstancePrayer.api.getPrayerTimesMakkah(Date,lat,lon)
    }
    suspend fun getPrayerTimesMadienah(Date:String,lat:Double , lon:Double): Response<Prayer> {
        return RetrofitInstancePrayer.api.getPrayerTimesMadienah(Date,lat,lon)
    }
    suspend fun getPrayerTimesAqsa(Date:String,lat:Double , lon:Double): Response<Prayer> {
        return RetrofitInstancePrayer.api.getPrayerTimesAqsa(Date,lat,lon)
    }
}