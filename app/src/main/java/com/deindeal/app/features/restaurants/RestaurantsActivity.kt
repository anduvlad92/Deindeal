package com.deindeal.app.features.restaurants

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.deindeal.app.R
import com.deindeal.app.features.base.BaseActivity
import com.deindeal.app.features.base.Resource
import com.deindeal.app.features.menu.MenuActivity
import com.deindeal.app.views.FilterListView

class RestaurantsActivity : BaseActivity<RestaurantsViewModel>() {


    override val layoutId: Int?
        get() = R.layout.activity_restaurants

    private lateinit var rvRestaurants: RecyclerView
    private lateinit var filtersListView: FilterListView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var rvRestaurantsAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.loadRestaurantsAndFilters()
    }

    override fun provideViewModel(): RestaurantsViewModel =
        RestaurantsViewModelFactory().create(RestaurantsViewModel::class.java)

    override fun subscribeUI() {

        setupRestaurantsRecyclerview()
        filtersListView = findViewById(R.id.filter_list_view)
        filtersListView.onFilterChanged = {
            viewmodel.changeFilter(it)
        }

        viewmodel.filters.observe(this, {
            when (it.state) {
                Resource.STATE.SUCCESS -> {
                    it.resource?.run {
                        filtersListView.loadFilters(this)
                        filtersListView.setFilter(viewmodel.selectedFilter)
                    }
                }
                Resource.STATE.ERROR -> {
                    showError("Error")
                }
            }
        })

        viewmodel.restaurants.observe(this, {
            when (it.state) {
                Resource.STATE.SUCCESS -> {
                    it.resource?.run {
                        rvRestaurantsAdapter.submitData(this)
                    }
                }
                Resource.STATE.ERROR -> {
                    showError("Error")
                }
            }
        })


    }

    private fun setupRestaurantsRecyclerview() {
        rvRestaurants = findViewById(R.id.rv_restaurants)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        swipeRefreshLayout.setOnRefreshListener {
            viewmodel.loadRestaurantsAndFilters()
        }

        rvRestaurants.adapter = RestaurantAdapter().apply {
            rvRestaurantsAdapter = this
            onRestaurantSelected = {
                MenuActivity.startFromRestaurantId(this@RestaurantsActivity, it.id, it.title)
            }
        }

        rvRestaurants.setHasFixedSize(true);
        val llm = LinearLayoutManager(this)
        rvRestaurants.layoutManager = llm
    }

    override fun showLoading() {
        if (swipeRefreshLayout.isRefreshing.not()) {
            super.showLoading()
        }
    }

    override fun hideLoading() {
        if (swipeRefreshLayout.isRefreshing)
            swipeRefreshLayout.isRefreshing = false
        else
            super.hideLoading()
    }
}