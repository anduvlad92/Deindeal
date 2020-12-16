package com.deindeal.app.features.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.deindeal.app.R
import com.deindeal.app.data.model.Menu

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    val items = ArrayList<Menu>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_view, null)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        items.get(position).run {
            holder.bind(this)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitData(data: List<Menu>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView
        val tvTitle: TextView
        val tvPrice: TextView

        init {
            ivImage = itemView.findViewById(R.id.iv_photo)
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvPrice = itemView.findViewById(R.id.tv_price)
        }

        fun bind(model: Menu) {
            tvTitle.text = model.title
            tvPrice.text = model.price
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