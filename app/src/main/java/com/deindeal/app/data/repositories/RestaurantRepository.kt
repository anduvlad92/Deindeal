package com.deindeal.app.data.repositories

import com.deindeal.app.data.sources.api.responses.GetRestaurantsResponse
import io.reactivex.Single

interface RestaurantRepository : BaseRepository {

    fun getRestaurantsAndFilters(): Single<GetRestaurantsResponse>
}