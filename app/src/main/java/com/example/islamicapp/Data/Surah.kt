package com.example.islamicapp.Data


data class Surah(val data: SurahData )
data class ListOfSurah(val data: List<SurahData> )
data class SurahData(
    val number: Int,
    val name: String,
    val englishName: String,
    val revelationType: String,
    val numberOfAyahs: Int,
    val ayahs: List<Ayah>
)
data class Ayah(
    val text: String,
    val numberInSurah: Int
)
