package com.deindeal.app.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deindeal.app.data.model.Filter
import com.deindeal.app.data.model.Restaurant
import com.deindeal.app.domain.usecase.FilterRestaurantsUseCase
import com.deindeal.app.domain.usecase.GetRestaurantsAndFiltersUseCase
import com.deindeal.app.features.base.BaseViewModel
import com.deindeal.app.features.base.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RestaurantsViewModel : BaseViewModel() {


    private val allRestaurants = ArrayList<Restaurant>()

    private val _filters = MutableLiveData<Resource<List<Filter>>>()
    private val _restaurants = MutableLiveData<Resource<List<Restaurant>>>()

    var selectedFilter: Filter? = null

    val filters: LiveData<Resource<List<Filter>>>
        get() = _filters

    val restaurants: LiveData<Resource<List<Restaurant>>>
        get() = _restaurants


    fun loadRestaurantsAndFilters() {
        addDisposable(
            GetRestaurantsAndFiltersUseCase().build()
                .flatMap {
                    allRestaurants.clear()
                    it.restaurants?.run {
                        allRestaurants.addAll(this)
                    }
                    it.filters?.run {
                        _filters.postValue(Resource(Resource.STATE.SUCCESS, this))
                    }
                    FilterRestaurantsUseCase().build(allRestaurants, selectedFilter)
                        .subscribeOn(Schedulers.computation())
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _progress.value = true
                }
                .subscribe({
                    _restaurants.value = Resource(Resource.STATE.SUCCESS, it)
                    _progress.value = false
                }, {
                    _progress.value = false
                    _restaurants.value = Resource(Resource.STATE.ERROR, null)
                })
        )
    }

    fun changeFilter(filter: Filter?) {
        selectedFilter = filter
        addDisposable(FilterRestaurantsUseCase().build(allRestaurants, selectedFilter)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _progress.value = true
            }.subscribe({
                _restaurants.value = Resource(Resource.STATE.SUCCESS, it)
                _progress.value = false
            }, {
                _progress.value = false
                _restaurants.value = Resource(Resource.STATE.ERROR, null)
            })
        )
    }


}