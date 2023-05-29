package com.denisardent.foodery.tabs.views.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisardent.foodery.App
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.FragmentHomeBinding
import com.denisardent.foodery.model.ErrorResult
import com.denisardent.foodery.model.restaurant.Restaurant
import com.denisardent.foodery.model.SuccessResult
import com.denisardent.foodery.tabs.views.BaseFragment
import com.denisardent.foodery.utils.ViewModelFactory

class HomeFragment: BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels{ ViewModelFactory(getAppContext()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val restaurantsAdapter = RestaurantsAdapter( object : RestaurantsAdapter.Listener{
            override fun onClickItem(restaurant: Restaurant) {
                Toast.makeText(context, restaurant.name, Toast.LENGTH_SHORT).show()
            }
        })

        binding.tryAgainButton.setOnClickListener {
            viewModel.tryAgain()
        }

        viewModel.restaurantsLiveData.observe(viewLifecycleOwner){ result ->
            Log.d("RESULT", "${result::class.java}")
            when (result){
                is SuccessResult -> {
                    Log.d("RESULT", "${result.data.size}")
                    binding.loading.visibility = View.GONE
                    restaurantsAdapter.restaurants = result.data
                    binding.error.visibility = View.GONE
                    restaurantsAdapter.notifyDataSetChanged()
                }
                is ErrorResult -> {
                    binding.errorTv.text = "There is some issue ${result.exception}"
                    binding.error.visibility = View.VISIBLE
                    binding.loading.visibility = View.GONE
                }
                else -> {
                    binding.loading.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                }
            }
        }


        binding.restaurantsRv.adapter = restaurantsAdapter
        binding.restaurantsRv.layoutManager = LinearLayoutManager(context)
    }
}