package com.example.bmiwithdb.bmi

import android.app.Application
import androidx.lifecycle.ViewModel

class calculatorViewModelFactory {
    private val dataSource: BMIDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(calculatorViewModel::class.java)) {
                return calculatorViewModel(dataSource, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}