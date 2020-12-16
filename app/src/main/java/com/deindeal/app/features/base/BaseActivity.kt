package com.deindeal.app.features.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deindeal.app.R

abstract class BaseActivity<VIEWMODEL : BaseViewModel>() :
    AppCompatActivity() {
    val viewmodel by lazy {
        return@lazy provideViewModel()
    }


    val progressView: View by lazy {
        return@lazy findViewById<FrameLayout>(R.id.progress_layout)
    }
    abstract val layoutId: Int?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutId?.run {
            setContentView(this)
        }

        viewmodel.progress.observe(this, {
            if (it)
                showLoading()
            else
                hideLoading()
        })
        subscribeUI()
    }


    open fun showLoading() {
        if (progressView.visibility != View.VISIBLE)
            progressView.visibility = View.VISIBLE
    }

    open fun hideLoading() {

        if (progressView.visibility != View.GONE)
            progressView.visibility = View.GONE
    }

    /**
     * Bind the livedata of the viewmodel
     */
    abstract fun subscribeUI()


    /**
     * Define implementation for providing the Viewmodel of this activity
     */
    abstract fun provideViewModel(): VIEWMODEL

    fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}