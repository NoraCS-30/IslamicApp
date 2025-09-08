package com.example.islamicapp.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.islamicapp.Repository.RepositorySurah
import com.example.islamicapp.ViewModel.HomePageViewModel

class HomePageViewModelFactory( private val repositorySurah: RepositorySurah):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomePageViewModel(repositorySurah) as T
    }
}