package com.deindeal.app.features.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.deindeal.app.data.model.Menu
import com.deindeal.app.data.model.RestaurantMenu
import com.deindeal.app.domain.model.Sort
import com.deindeal.app.domain.usecase.GetRestaurantMenuUseCase
import com.deindeal.app.domain.usecase.SortMenusUseCase
import com.deindeal.app.features.base.BaseViewModel
import com.deindeal.app.features.base.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MenuViewModel(val restaurantId: Long) : BaseViewModel() {
    private var restaurantMenu: RestaurantMenu? = null

    private val _menuList = MutableLiveData<Resource<List<Menu>>>()

    val menuList: LiveData<Resource<List<Menu>>>
        get() = _menuList


    fun loadMenu() {
        addDisposable(
            GetRestaurantMenuUseCase().build(restaurantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _progress.value = true
                }
                .subscribe({
                    _progress.value = false
                    restaurantMenu = it
                    _menuList.value = Resource(Resource.STATE.SUCCESS, it.menu)
                }, {
                    _menuList.value = Resource(Resource.STATE.ERROR, null)
                    _progress.value = false
                })
        )
    }


    fun sortMenu(sort: Sort) {
        restaurantMenu?.menu?.run {
            SortMenusUseCase().build(this, sort)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _progress.value = true
                }
                .subscribe({
                    _progress.value = false
                    _menuList.value = Resource(Resource.STATE.SUCCESS, it)
                }, {
                    _menuList.value = Resource(Resource.STATE.ERROR, null)
                    _progress.value = false
                })
        }
    }
}