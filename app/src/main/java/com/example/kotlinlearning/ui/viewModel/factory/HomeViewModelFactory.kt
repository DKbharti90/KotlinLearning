package com.example.kotlinlearning.ui.viewModel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlearning.data.AppDatabase
import com.example.kotlinlearning.ui.viewModel.HomeViewModel
import com.example.kotlinlearning.ui.viewModel.MainViewModel

class HomeViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val moduleDao = AppDatabase.get(app).moduleDao()
            @Suppress("UNCHECKED_CAST") // Guaranteed to succeed at this point.
            return HomeViewModel(moduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}