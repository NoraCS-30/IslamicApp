package com.example.islamicapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islamicapp.Data.ListOfSurah
import com.example.islamicapp.Repository.RepositorySurah
import kotlinx.coroutines.launch
import retrofit2.Response

class SurahQuranViewModel(private val repositorySurah: RepositorySurah) : ViewModel() {
    val ResponseListOfSurah: MutableLiveData<Response<ListOfSurah>> = MutableLiveData()
    fun getListOfSurah(){
        viewModelScope.launch {
            val response=repositorySurah.getListOfSurah()
            ResponseListOfSurah.value=response

        }
    }

}