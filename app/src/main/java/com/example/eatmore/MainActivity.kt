package com.example.eatmore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodCategory.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.HORIZONTAL, false
            )
            adapter = CategoryAdapter(
                this@MainActivity,
                addCategory()
            ) { pos -> onCatItemClicked(pos) }
        }

        foodItems.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.HORIZONTAL, false
            )
        }

        LinearSnapHelper().attachToRecyclerView(foodCategory)
        LinearSnapHelper().attachToRecyclerView(foodItems)

    }

    private fun onCatItemClicked(pos: Int) {
        val items = mutableListOf<FoodItem>()
        when (pos) {
            0 -> {
                foodItem.text = this.resources.getString(R.string.burger)
                items.add(FoodItem("Burger", 4.5f, 14, R.drawable.burger_two))
                items.add(FoodItem("Burger one ", 5f, 14, R.drawable.burger))
                items.add(FoodItem("Burger two", 4f, 14, R.drawable.burger_two))
                items.add(FoodItem("Burger", 3.5f, 14, R.drawable.burger))
            }
            1 -> {
                foodItem.text = this.resources.getString(R.string.chicken)
                items.add(FoodItem("Chicken", 4.5f, 14, R.drawable.grill_chicken_1))
                items.add(FoodItem("Chicken one ", 5f, 14, R.drawable.grill_chicken_2))
                items.add(FoodItem("Chicken two", 4f, 14, R.drawable.grill_chicken_3))
                items.add(FoodItem("Chicken", 3.5f, 14, R.drawable.grill_chicken_2))
            }
            2 -> {
                foodItem.text = this.resources.getString(R.string.pizza)
                items.add(FoodItem("Pizza", 4.5f, 14, R.drawable.pizza_1))
                items.add(FoodItem("Pizza one ", 5f, 14, R.drawable.pizza_2))
                items.add(FoodItem("Pizza two", 4f, 14, R.drawable.pizza_3))
                items.add(FoodItem("Pizza", 3.5f, 14, R.drawable.pizza_4))
            }
        }
        foodItems.apply {
            adapter = FoodItemAdapter(this@MainActivity, items)
        }
    }

    private fun addCategory(): List<FoodCategory> {
        return mutableListOf(
            FoodCategory("Burger", R.drawable.ic_burger),
            FoodCategory("Chicken", R.drawable.ic_chicken),
            FoodCategory("Pizza", R.drawable.ic_pizza)
        )
    }

}