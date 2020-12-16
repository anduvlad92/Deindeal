package com.deindeal.app.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.deindeal.app.R

class FilterView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    val ivFilterPhoto: ImageView
    val tvFilterName: TextView

    init {
        orientation = VERTICAL
        inflate(context, R.layout.layout_filter_view, this)
        ivFilterPhoto = findViewById(R.id.iv_filter_photo)
        tvFilterName = findViewById(R.id.tv_filter_name)
    }

    fun setSelectedState() {
        setBackgroundColor(Color.RED)
    }

    fun clearSelected() {
        setBackgroundColor(Color.TRANSPARENT)
    }
}