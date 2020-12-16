package com.deindeal.app.data.sources.api.services

import com.deindeal.app.data.model.RestaurantMenu
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantMenuService : BaseService {
    @GET("/{restaurant_id}/restaurant.json")
    fun getRestaurantMenu(@Path("restaurant_id") restaurantId: Long): Single<RestaurantMenu>
}