package com.example.islamicapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedSurah = MutableLiveData<Int>()
    val selectedSurah: LiveData<Int> get() = _selectedSurah

    fun setSurah(number: Int) {
        _selectedSurah.value = number
    }

}