package com.denisardent.foodery.tabs.views.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.FragmentHomeBinding
import com.denisardent.foodery.model.accounts.entities.Restaurant
import com.denisardent.foodery.utils.base.BaseFragment
import com.denisardent.foodery.utils.ViewModelFactory

class HomeFragment: BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels{ ViewModelFactory(getAppContext()) }
    private lateinit var binding: FragmentHomeBinding
    private lateinit var restaurantsAdapter: RestaurantsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)


        restaurantsAdapter = RestaurantsAdapter( object : RestaurantsAdapter.Listener{
            override fun onClickItem(restaurant: Restaurant) {
                val direction = HomeFragmentDirections.actionHomeFragmentToRestaurantFragment(restaurant.id)
                findNavController().navigate(direction)
            }
        })

        handleResult(viewModel.restaurantsList)

        binding.tryAgainButton.setOnClickListener {
            viewModel.tryAgain()
        }


        binding.restaurantsRv.adapter = restaurantsAdapter
        binding.restaurantsRv.layoutManager = LinearLayoutManager(context)
    }

    override fun <T> onSuccessed(element: T) {
        val restaurantsList = element as List<Restaurant>
        binding.loading.visibility = View.GONE
        restaurantsAdapter.restaurants = restaurantsList
        binding.error.visibility = View.GONE
        restaurantsAdapter.notifyDataSetChanged()
    }

    override fun onErrored(e: Exception) {
        binding.errorTv.text = "There is some issue $e"
        binding.error.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
    }

    override fun onPending() {
        binding.loading.visibility = View.VISIBLE
        binding.error.visibility = View.GONE
    }
}