package com.deindeal.app.domain.usecase

import com.deindeal.app.data.DataProvider
import com.deindeal.app.data.sources.api.responses.GetRestaurantsResponse
import io.reactivex.Single

class GetRestaurantsAndFiltersUseCase {

    fun build(): Single<GetRestaurantsResponse> =
        DataProvider.restaurantRepository.getRestaurantsAndFilters()
}