package com.deindeal.app.data.repositories

import com.deindeal.app.data.model.RestaurantMenu
import io.reactivex.Single

interface RestaurantMenuRepository : BaseRepository {
    fun getRestaurantMenu(restaurantId: Long): Single<RestaurantMenu>
}