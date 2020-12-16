package com.deindeal.app.domain.usecase

import com.deindeal.app.data.DataProvider
import com.deindeal.app.data.model.RestaurantMenu
import io.reactivex.Single

class GetRestaurantMenuUseCase {
    fun build(id: Long): Single<RestaurantMenu> =
        DataProvider.restaurantMenuRepository.getRestaurantMenu(id)
}