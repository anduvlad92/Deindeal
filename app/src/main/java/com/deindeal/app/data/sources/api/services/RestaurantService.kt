package com.deindeal.app.data.sources.api.services

import com.deindeal.app.data.sources.api.responses.GetRestaurantsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RestaurantService : BaseService {


    @GET("/restaurants.json")
    fun getRestaurants(): Single<GetRestaurantsResponse>

}