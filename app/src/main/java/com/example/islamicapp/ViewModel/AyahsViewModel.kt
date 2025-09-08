package com.example.islamicapp.ViewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.Data.Surah
import com.example.islamicapp.Repository.RepositorySurah
import kotlinx.coroutines.launch
import retrofit2.Response


class AyahsViewModel(private val repositorySurah: RepositorySurah) : ViewModel()  {

    val ResponseSurah: MutableLiveData<Response<Surah>> = MutableLiveData()
    fun getSurah(numberOfSurah:Int){
        viewModelScope.launch {
            val response = repositorySurah.getSurah(numberOfSurah)
            ResponseSurah.value = response
        }
    }

}