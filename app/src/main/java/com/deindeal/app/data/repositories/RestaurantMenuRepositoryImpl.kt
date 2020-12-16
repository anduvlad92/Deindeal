package com.deindeal.app.data.repositories

import com.deindeal.app.data.model.RestaurantMenu
import com.deindeal.app.data.sources.api.services.RestaurantMenuService
import io.reactivex.Single

class RestaurantMenuRepositoryImpl(apiService: RestaurantMenuService) :
    BaseRepositoryImpl<RestaurantMenuService>(
        apiService
    ), RestaurantMenuRepository {

    override fun getRestaurantMenu(restaurantId: Long): Single<RestaurantMenu> =
        apiService.getRestaurantMenu(restaurantId)
}