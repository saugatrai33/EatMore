package com.example.eatmore

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import java.util.*

/**
 * Adapter for category items
 * */
class CategoryAdapter(
    private val context: Context,
    private val foodCat: List<FoodCategory>,
    private val onCatClick: (pos: Int) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedCat = 0

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal val catName: TextView = itemView.findViewById(R.id.catName)
        internal val catImg: ImageView = itemView.findViewById(R.id.catImg)
        internal val catItem: MaterialCardView = itemView.findViewById(R.id.catItem)

        init {
            catItem.setOnClickListener {
                selectedCat = adapterPosition
                onCatClick(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = foodCat.size

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val cat: FoodCategory = foodCat[position]
        holder.catName.text = cat.name
        holder.catImg.setImageResource(cat.image)
        if (position == selectedCat) {
            // Make card selected
            holder.catItem.outlineSpotShadowColor = context.getColor(R.color.red)
            holder.catItem.outlineAmbientShadowColor = context.getColor(R.color.red)
            holder.catItem.strokeWidth = 2;
            holder.catName.setTextColor(context.getColor(R.color.red));
            holder.catImg.setColorFilter(
                ContextCompat.getColor(context, R.color.red),
                PorterDuff.Mode.SRC_IN
            );
        } else {
            // Make card inactive
            holder.catItem.outlineSpotShadowColor = context.getColor(R.color.gray1);
            holder.catItem.outlineAmbientShadowColor = context.getColor(R.color.gray);
            holder.catName.setTextColor(context.getColor(R.color.gray));
            holder.catImg.setColorFilter(
                ContextCompat.getColor(context, R.color.gray1),
                PorterDuff.Mode.SRC_IN
            );
            holder.catItem.strokeWidth = 0;
        }
    }

}