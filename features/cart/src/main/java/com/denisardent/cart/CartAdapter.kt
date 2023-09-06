package com.denisardent.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denisardent.cart.databinding.ItemDishBinding
import com.denisardent.common.entities.DishItem

class CartAdapter(
    private val listener: Listener
): ListAdapter<DishItem, CartAdapter.DishHolder>(ItemCallback), View.OnClickListener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDishBinding.inflate(inflater, parent, false)
        binding.buttonMin.setOnClickListener(this)
        binding.buttonPlus.setOnClickListener(this)

        return DishHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DishHolder, position: Int) {
        val dish = getItem(position)

        with(holder.binding){
            Glide
                .with(root)
                .load(dish.imageUrl)
                .into(dishIv)
            buttonPlus.tag = dish
            buttonMin.tag = dish
            dishName.text = dish.name
            dishCost.text = dish.price.toString()+"₽"
            count.text = dish.quantity.toString()
            dishWeight.text = dish.weight.toString()+"g"
        }
    }

    override fun onClick(v: View) {
        val dishItem = v.tag as DishItem
        if (v.id == R.id.button_plus) listener.onIncreased(dishItem)
        if (v.id == R.id.button_min) listener.onDecreased(dishItem)
    }

    fun getDishId(itemId: Int): Int{
        return getItem(itemId).id
    }


    class DishHolder(
        val binding: ItemDishBinding
    ) : RecyclerView.ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<DishItem>() {
        override fun areItemsTheSame(oldItem: DishItem, newItem: DishItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DishItem, newItem: DishItem): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener{

        fun onIncreased(dishItem: DishItem)

        fun onDecreased(dishItem: DishItem)
    }
}