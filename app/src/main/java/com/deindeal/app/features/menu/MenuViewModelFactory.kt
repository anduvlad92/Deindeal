package com.deindeal.app.features.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MenuViewModelFactory(val restaurantId: Long) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return MenuViewModel(restaurantId) as T
    }
}