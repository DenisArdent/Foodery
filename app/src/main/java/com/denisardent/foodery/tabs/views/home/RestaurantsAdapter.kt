package com.denisardent.foodery.tabs.views.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denisardent.foodery.databinding.RestaurantBinding
import com.denisardent.foodery.model.restaurant.Restaurant

class RestaurantsAdapter(
    // adding listener for fragment
    private val listener: Listener
    ): RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>(), View.OnClickListener {

    var restaurants: List<Restaurant>? = null

    class RestaurantViewHolder(val binding: RestaurantBinding): RecyclerView.ViewHolder(binding.root)


    // overriding onclick function for elements
    override fun onClick(v: View) {
        val restaurant = v.tag as Restaurant
        listener.onClickItem(restaurant)
    }


    override fun getItemCount(): Int {
        return restaurants?.size ?:0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RestaurantBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants?.get(position) ?: return
        with(holder.binding) {
            holder.itemView.tag = restaurant
            restaurantNameTv.text = restaurant.name
            restaurantIv.setImageResource(restaurant.restaurantLogo)
            rating.text = restaurant.rating
            restaurantTypeTv.text = restaurant.foodType
            deliveringTimeTv.text = restaurant.deliveryTime.toString()
            discountPercentage.text = restaurant.discountPercentage.toString() + "% OFF"
        }
    }

    interface Listener{
        fun onClickItem(restaurant: Restaurant)
    }


}