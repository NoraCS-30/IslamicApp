package com.example.islamicapp.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.islamicapp.Repository.RepositorySurah
import com.example.islamicapp.ViewModel.AyahsViewModel
import com.example.islamicapp.ViewModel.SurahQuranViewModel

class AyahsViewModelFactory(private val repositorySurah: RepositorySurah): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AyahsViewModel(repositorySurah) as T
    }
}