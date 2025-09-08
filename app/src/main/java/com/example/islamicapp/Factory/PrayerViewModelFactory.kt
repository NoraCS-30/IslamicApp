package com.example.islamicapp.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.islamicapp.Repository.RepositoryPrayer
import com.example.islamicapp.ViewModel.PrayerViewModel


class PrayerViewModelFactory (private val repositoryPrayer: RepositoryPrayer): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PrayerViewModel(repositoryPrayer) as T
    }
}