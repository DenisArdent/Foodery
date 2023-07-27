package com.denisardent.home.presentation.home.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denisardent.common.entities.DishInfo
import com.denisardent.home.databinding.ItemDishinfoBinding

class RestaurantAdapter(private val listener: Listener): ListAdapter<DishInfo, RestaurantAdapter.DishHolder>(ItemCallback), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDishinfoBinding.inflate(inflater, parent, false)
        binding.buttonAdd.setOnClickListener(this)

        return DishHolder(binding)
    }

    override fun onBindViewHolder(holder: DishHolder, position: Int) {
        val dishInfo = getItem(position)

        with(holder.binding){
            buttonAdd.tag = dishInfo
            dishNameTv.text = dishInfo.name
            Glide
                .with(root)
                .load(dishInfo.imageUrl)
                .into(dishIv)
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<DishInfo>() {
        override fun areItemsTheSame(oldItem: DishInfo, newItem: DishInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DishInfo, newItem: DishInfo): Boolean {
            return oldItem == newItem
        }
    }

    class DishHolder(
        val binding: ItemDishinfoBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val dishInfo = v.tag as DishInfo
        listener.onAddClicked(dishInfo)
    }

    interface Listener{
        fun onAddClicked(dishInfo: DishInfo)
    }
}