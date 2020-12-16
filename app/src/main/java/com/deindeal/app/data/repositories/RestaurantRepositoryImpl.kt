package com.deindeal.app.data.repositories

import com.deindeal.app.data.sources.api.services.RestaurantService

class RestaurantRepositoryImpl(apiService: RestaurantService) :
    BaseRepositoryImpl<RestaurantService>(
        apiService
    ), RestaurantRepository {

    override fun getRestaurantsAndFilters()= apiService.getRestaurants()
}