package com.example.islamicapp.Api



import com.example.islamicapp.Data.Prayer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PrayerApi {
    @GET("timings/{date}")
   suspend fun getPrayerTimes(  @Path("date") date: String,
                                @Query("latitude") latitude: Double,
                                @Query("longitude") longitude: Double
    ): Response<Prayer>

    @GET("timings/{date}")
    suspend fun getPrayerTimesMakkah(  @Path("date") date: String,
                                 @Query("latitude") latitude: Double,
                                 @Query("longitude") longitude: Double
    ): Response<Prayer>

    @GET("timings/{date}")
    suspend fun getPrayerTimesMadienah(  @Path("date") date: String,
                                       @Query("latitude") latitude: Double,
                                       @Query("longitude") longitude: Double
    ): Response<Prayer>
    @GET("timings/{date}")
    suspend fun getPrayerTimesAqsa(  @Path("date") date: String,
                                       @Query("latitude") latitude: Double,
                                       @Query("longitude") longitude: Double
    ): Response<Prayer>




    /*
    Makkah
        Latitude	21.422510
        Longitude	39.826168*/

   /*
   Madienah
   Latitude	24.470901
Longitude	39.612236*/

   /*aqsa
   31.77614,35.23549*/

}