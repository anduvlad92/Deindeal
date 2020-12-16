package com.deindeal.app.features.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.deindeal.app.R
import com.deindeal.app.data.model.Restaurant

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    val items = ArrayList<Restaurant>()
    var onRestaurantSelected: ((restaurant: Restaurant) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_restaurant_view, null)
        view.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)

        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        items.get(position).run {
            holder.bind(this)
        }
        holder.itemView.setOnClickListener {
            onRestaurantSelected?.run {
                invoke(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitData(data: List<Restaurant>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivImage: ImageView
        val tvTitle: TextView
        val tvSubTitle: TextView

        init {
            ivImage = itemView.findViewById(R.id.iv_photo)
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvSubTitle = itemView.findViewById(R.id.tv_sub_title)
        }

        fun bind(model: Restaurant) {
            tvTitle.text = model.title
            tvSubTitle.text = model.subtitle
            Glide.with(itemView)
                .load(model.image)
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(ivImage)
        }
    }
}