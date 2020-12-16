package com.deindeal.app.views

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.deindeal.app.R
import com.deindeal.app.data.model.Filter
import com.deindeal.app.utils.svg.SvgSoftwareLayerSetter

class FilterListView : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var currentFilter: Filter? = null
    var onFilterChanged: ((filter: Filter?) -> Unit)? = null


    init {
        orientation = HORIZONTAL
    }

    fun loadFilters(list: List<Filter>) {
        removeAllViews()
        currentFilter = null
        list.forEach {
            loadFilter(it)
        }
    }

    private fun loadFilter(filter: Filter) {
        val view = FilterView(context).apply {
            Glide.with(this)
                .`as`(PictureDrawable::class.java)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .transition(withCrossFade())
                .listener(SvgSoftwareLayerSetter())
                .load(filter.icon)
                .into(ivFilterPhoto)
            tvFilterName.text = filter.label

        }
        view.tag = filter

        view.setOnClickListener {
            (it.tag as Filter).run {
                setFilter(this)
                onFilterChanged?.invoke(currentFilter)
            }
        }
        addView(view)
    }

    fun setFilter(filter: Filter?) {
        if (filter == null) {
            currentFilter?.run {
                findViewWithTag<FilterView>(currentFilter).clearSelected()
            }
            currentFilter = null
        } else {
            val nextSelectedView = findViewWithTag<FilterView>(filter)
            currentFilter?.run {
                findViewWithTag<FilterView>(currentFilter).clearSelected()
            }
            if (filter.equals(currentFilter).not()) {
                nextSelectedView.setSelectedState()
                currentFilter = filter
            } else {
                currentFilter = null
            }
        }

    }
}