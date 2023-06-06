package com.denisardent.foodery.tabs.views.home.restaurant

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.FragmentRestaurantBinding
import com.denisardent.foodery.model.accounts.entities.Restaurant
import com.denisardent.foodery.utils.base.BaseFragment
import com.denisardent.foodery.utils.ViewModelFactory
import kotlinx.coroutines.launch

class RestaurantFragment: BaseFragment(R.layout.fragment_restaurant) {

    val viewModel: RestaurantViewModel by viewModels<RestaurantViewModel>{ ViewModelFactory(getAppContext()) }
    val args: RestaurantFragmentArgs by navArgs()
    private lateinit var binding: FragmentRestaurantBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRestaurantBinding.bind(view)
        val restaurantId = args.restaurantId.toInt()-1
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        lifecycleScope.launch{
            viewModel.getCurrentRestaurant(restaurantId)
        }

        handleResult(viewModel.restaurantInfo)

        binding.likeRestaurantCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.changeRestaurantState(isChecked, (restaurantId+1).toLong())
        }
    }

    override fun <T> onSuccessed(element: T) {
        val restaurant = element as Restaurant
        binding.likeRestaurantCheckbox.isEnabled = true
        binding.likeRestaurantCheckbox.isChecked = restaurant.isLiked

        binding.mainRestaurantName.text = restaurant.name
        binding.mainRating.text = restaurant.rating
        binding.mainRestaurantTypeTv.text = restaurant.foodType.toString()
        binding.mainDeliveringTimeTv.text = restaurant.deliveryTime.toString()
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