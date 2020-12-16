package com.deindeal.app.domain.usecase

import com.deindeal.app.data.model.Menu
import com.deindeal.app.domain.model.Sort
import com.deindeal.app.domain.model.Sort.*
import io.reactivex.Observable
import io.reactivex.Single
import java.math.BigDecimal

class SortMenusUseCase {
    fun build(menus: List<Menu>, sort: Sort): Single<List<Menu>> = Observable.fromIterable(menus)
        .sorted { o1, o2 ->
            val o1Price = BigDecimal(o1.price)
            val o2Price = BigDecimal(o2.price)
            when (sort) {
                PRICE_ASCENDING -> {
                    if (o1Price > o2Price) {
                        return@sorted 1
                    } else {
                        return@sorted -1
                    }
                }
                PRICE_DESCENDING -> {
                    if (o1Price < o2Price) {
                        return@sorted 1
                    } else {
                        return@sorted -1
                    }
                }
            }
            0
        }.toList()
}