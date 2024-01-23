package com.denisardent.home.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denisardent.common.entities.Restaurant
import com.denisardent.home.R
import com.denisardent.home.databinding.FragmentHomeBinding
import com.denisardent.home.presentation.home.HomeAdapter
import com.denisardent.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment(R.layout.fragment_home) {


    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)


        homeAdapter = HomeAdapter( object : HomeAdapter.Listener {
            override fun onClickItem(restaurant: Restaurant) {
                findNavController().navigate(R.id.restaurantFragment, bundleOf(
                    "restaurant_id" to restaurant.id
                ))
            }
        })

        handleResult(viewModel.restaurantsList)

        binding.locationButton.setOnClickListener {
            findNavController().navigate(R.id.mapFragment)
        }

        binding.tryAgainButton.setOnClickListener {
        //    viewModel.tryAgain()
        }


        binding.restaurantsRv.adapter = homeAdapter
        binding.restaurantsRv.layoutManager = LinearLayoutManager(context)
    }

    @SuppressLint("NotifyDataSetChanged")
    @Suppress("UNCHECKED_CAST")
    override fun <T> onSucceed(state: T) {
        val restaurantsList = state as List<Restaurant>
        binding.loading.visibility = View.GONE
        homeAdapter.restaurants = restaurantsList
        binding.error.visibility = View.GONE
        homeAdapter.notifyDataSetChanged()
    }

    override fun onErrored(e: Exception) {
        binding.errorTv.text = getString(R.string.there_is_some_issue, e.toString())
        binding.error.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
    }

    override fun onPending() {
        binding.loading.visibility = View.VISIBLE
        binding.error.visibility = View.GONE
    }
}