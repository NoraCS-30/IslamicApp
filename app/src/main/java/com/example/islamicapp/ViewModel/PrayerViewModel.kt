package com.example.islamicapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.Data.Prayer
import com.example.islamicapp.Repository.RepositoryPrayer
import kotlinx.coroutines.launch
import retrofit2.Response


class PrayerViewModel(private val repositoryPrayer: RepositoryPrayer):ViewModel() {
    val responsePrayer: MutableLiveData<Response<Prayer>> = MutableLiveData()
    fun getPrayerTimes(Date:String ,lat:Double , lon:Double){
        viewModelScope.launch {
            val response=repositoryPrayer.getPrayerTimes(Date,lat, lon)
           responsePrayer.value=response
        }
    }

    val responsePrayerMakkah: MutableLiveData<Response<Prayer>> = MutableLiveData()
    fun getPrayerTimesMakkah(Date:String ,lat:Double , lon:Double){
        viewModelScope.launch {
            val response=repositoryPrayer.getPrayerTimesMakkah(Date,lat, lon)
            responsePrayerMakkah.value=response
        }
    }

    val responsePrayerMadienah: MutableLiveData<Response<Prayer>> = MutableLiveData()
    fun getPrayerTimesMadienah(Date:String ,lat:Double , lon:Double){
        viewModelScope.launch {
            val response = repositoryPrayer.getPrayerTimesMadienah(Date, lat, lon)
            responsePrayerMadienah.value = response
        }
    }

    val responsePrayerAqsa: MutableLiveData<Response<Prayer>> = MutableLiveData()
    fun getPrayerTimesAqsa(Date:String ,lat:Double , lon:Double){
        viewModelScope.launch {
            val response = repositoryPrayer.getPrayerTimesAqsa(Date, lat, lon)
            responsePrayerAqsa.value = response
        }
    }

}