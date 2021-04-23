package com.example.eatmore

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class FoodItemAdapter(
    private val context: Context,
    private val foodItems: List<FoodItem>
) : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    private var selectedFoodItem = 0

    inner class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val foodItemImg: ImageView = itemView.findViewById(R.id.foodItemImg)
        internal val foodName: TextView = itemView.findViewById(R.id.foodName)
        internal val rating: RatingBar = itemView.findViewById(R.id.rating)
        internal val price: TextView = itemView.findViewById(R.id.price)
        internal val item: MaterialCardView = itemView.findViewById(R.id.item)
        internal val llbg: LinearLayout = itemView.findViewById(R.id.llbg)

        init {
            item.setOnClickListener {
                selectedFoodItem = adapterPosition
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return FoodItemViewHolder(view)
    }

    override fun getItemCount(): Int = foodItems.size

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val foodItem = foodItems[position]
        holder.foodItemImg.setImageResource(foodItem.image)
        holder.foodName.text = foodItem.name
        holder.rating.rating = foodItem.rating
        holder.price.text = context.resources.getString(R.string.price, foodItem.price)

        if (selectedFoodItem == position) {
            holder.item.animate().scaleX(1.1f)
            holder.item.animate().scaleY(1.1f)
            holder.foodName.setTextColor(Color.WHITE)
            holder.price.setTextColor(Color.WHITE)
            holder.llbg.setBackgroundResource(R.drawable.splash)
        } else {
            holder.item.animate().scaleX(1f)
            holder.item.animate().scaleY(1f)
            holder.foodName.setTextColor(Color.BLACK)
            holder.price.setTextColor(Color.BLACK)
            holder.llbg.setBackgroundResource(R.color.white)
        }
    }

}