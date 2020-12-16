package com.deindeal.app.data

import com.deindeal.app.data.repositories.RestaurantMenuRepositoryImpl
import com.deindeal.app.data.repositories.RestaurantRepositoryImpl
import com.deindeal.app.data.sources.api.ApiSource
import com.deindeal.app.data.sources.api.services.RestaurantMenuService
import com.deindeal.app.data.sources.api.services.RestaurantService

/**
 * Object that initializes all the data sources of the app (api, databases, sharedpreferences etc)
 */
object DataProvider {

    val restaurantRepository =
        RestaurantRepositoryImpl(ApiSource.createService(RestaurantService::class.java))

    val restaurantMenuRepository =
        RestaurantMenuRepositoryImpl(ApiSource.createService(RestaurantMenuService::class.java))

}