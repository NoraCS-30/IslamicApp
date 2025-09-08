package com.example.islamicapp.Data

data class Prayer( val data: Prayertimings)
data class Prayertimings(val timings: Timings)
data class Timings(
    val Fajr: String,
    val Dhuhr: String,
    val Asr: String,
    val Sunset: String,
    val Maghrib: String,
    val Isha: String,
)