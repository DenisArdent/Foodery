package com.denisardent.home.presentation.home.restaurant

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.denisardent.common.entities.DishInfo
import com.denisardent.home.R
import com.denisardent.home.databinding.FragmentRestaurantBinding
import com.denisardent.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RestaurantFragment: BaseFragment(R.layout.fragment_restaurant) {

    private val viewModel: RestaurantViewModel by viewModels()

    private lateinit var binding: FragmentRestaurantBinding
    private lateinit var adapter: RestaurantAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRestaurantBinding.bind(view)
        adapter = RestaurantAdapter(object: RestaurantAdapter.Listener{
            override fun onAddClicked(dishInfo: DishInfo) {
                viewModel.onDishAdded(dishInfo.id)
            }
        })
        binding.recyclerView.adapter = adapter
        val restaurantId = requireArguments().getLong("restaurant_id")
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        lifecycleScope.launch{
            viewModel.getCurrentRestaurant(restaurantId.toInt()-1)
        }



        handleResult(viewModel.state)


        binding.likeRestaurantCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("RESTFRAGMENT", "$restaurantId")
            viewModel.changeRestaurantState(isChecked, (restaurantId))
        }
    }

    override fun <T> onSucceed(state: T) {
        val result = state as RestaurantState

        binding.likeRestaurantCheckbox.isEnabled = true
        binding.likeRestaurantCheckbox.isChecked = state.restaurantInfo.isLiked

        binding.mainRestaurantName.text = result.restaurantInfo.name
        binding.mainRating.text = result.restaurantInfo.rating
        binding.mainRestaurantTypeTv.text = result.restaurantInfo.foodType
        binding.mainDeliveringTimeTv.text = result.restaurantInfo.deliveryTime.toString()
        binding.mainComponents.visibility = View.VISIBLE
        binding.loading.visibility = View.INVISIBLE

        adapter.submitList(result.dishList)

        binding.mainComponents.visibility = View.VISIBLE
        binding.loading.visibility = View.INVISIBLE

    }

    override fun onErrored(e: Exception) {
        binding.mainComponents.visibility = View.INVISIBLE
    }

    override fun onPending() {
        binding.likeRestaurantCheckbox.isEnabled = false
        binding.mainComponents.visibility = View.INVISIBLE
        binding.loading.visibility = View.VISIBLE
    }
}
