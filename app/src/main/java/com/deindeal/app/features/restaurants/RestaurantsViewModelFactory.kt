package com.deindeal.app.features.restaurants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RestaurantsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return RestaurantsViewModel() as T
    }
}