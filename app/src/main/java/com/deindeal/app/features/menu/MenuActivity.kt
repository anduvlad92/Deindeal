package com.deindeal.app.features.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.deindeal.app.R
import com.deindeal.app.domain.model.Sort
import com.deindeal.app.features.base.BaseActivity
import com.deindeal.app.features.base.Resource

class MenuActivity : BaseActivity<MenuViewModel>() {
    override val layoutId: Int? = R.layout.activity_menu

    private lateinit var rvMenus: RecyclerView
    private lateinit var btnSortAscending: Button
    private lateinit var btnSortDescending: Button
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var rvMenuAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(intent.getStringExtra(EXTRA_RESTAURANT_NAME))
        viewmodel.loadMenu()
    }

    override fun subscribeUI() {
        setupMenuRecyclerview()
        setupSortButtons()
        viewmodel.menuList.observe(this, {
            when (it.state) {
                Resource.STATE.SUCCESS -> {
                    it.resource?.run {
                        rvMenuAdapter.submitData(this)
                    }
                }
                Resource.STATE.ERROR -> {
                    showError("Error")
                }
            }
        })
    }

    override fun provideViewModel(): MenuViewModel =
        MenuViewModelFactory(
            intent.getLongExtra(
                EXTRA_RESTAURANT_ID,
                -1
            )
        ).create(MenuViewModel::class.java)


    private fun setupSortButtons() {
        btnSortAscending = findViewById(R.id.btn_sort_price_a)
        btnSortDescending = findViewById(R.id.btn_sort_price_d)

        btnSortAscending.setOnClickListener {
            viewmodel.sortMenu(Sort.PRICE_ASCENDING)
        }

        btnSortDescending.setOnClickListener {
            viewmodel.sortMenu(Sort.PRICE_DESCENDING)
        }
    }

    private fun setupMenuRecyclerview() {
        rvMenus = findViewById(R.id.rv_menus)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        swipeRefreshLayout.setOnRefreshListener {
            viewmodel.loadMenu()
        }

        rvMenus.adapter = MenuAdapter().apply {
            rvMenuAdapter = this
        }

        rvMenus.setHasFixedSize(true);
        val glm = GridLayoutManager(this, 2)
        rvMenus.layoutManager = glm
    }

    override fun showLoading() {
        if (swipeRefreshLayout.isRefreshing.not())
            super.showLoading()
    }

    override fun hideLoading() {
        if (swipeRefreshLayout.isRefreshing)
            swipeRefreshLayout.isRefreshing = false
        else
            super.hideLoading()
    }

    companion object {
        val EXTRA_RESTAURANT_ID = "restaurant-id"
        val EXTRA_RESTAURANT_NAME = "restaurant-name"
        fun startFromRestaurantId(context: Context, id: Long, name: String?) {
            Intent(context, MenuActivity::class.java).apply {
                putExtra(EXTRA_RESTAURANT_ID, id)
                putExtra(EXTRA_RESTAURANT_NAME, name)
                context.startActivity(this)
            }
        }
    }
}