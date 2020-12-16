package com.deindeal.app.domain.usecase

import com.deindeal.app.data.model.Filter
import com.deindeal.app.data.model.Restaurant
import io.reactivex.Observable
import io.reactivex.Single

class FilterRestaurantsUseCase {
    fun build(source: List<Restaurant>, filter: Filter?): Single<List<Restaurant>> = filter?.run {
        Observable.fromIterable(source)
            .filter {
                it.filters?.contains(filter.id) ?: false
            }.toList()
    } ?: Single.just(source)
}