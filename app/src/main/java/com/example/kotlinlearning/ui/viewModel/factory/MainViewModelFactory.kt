package com.example.kotlinlearning.ui.viewModel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinlearning.data.AppDatabase
import com.example.kotlinlearning.ui.viewModel.MainViewModel

/**
 * A [ViewModelProvider.Factory] that provides dependencies to [CheeseViewModel],
 * allowing tests to switch out [CheeseDao] implementation via constructor injection.
 */
class MainViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val cheeseDao = AppDatabase.get(app).cheeseDao()
            @Suppress("UNCHECKED_CAST") // Guaranteed to succeed at this point.
            return MainViewModel(cheeseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}