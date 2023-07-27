package com.denisardent.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.denisardent.cart.databinding.FragmentCartBinding
import com.denisardent.common.entities.DishItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment: Fragment(R.layout.fragment_cart) {
    lateinit var binding: FragmentCartBinding
    val viewModel: CartViewModel by viewModels()
    lateinit var adapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view)
        adapter = CartAdapter(object: CartAdapter.Listener{
            override fun onIncreased(dishItem: DishItem) {

                viewModel.changeDishItemQuantity(dishItem.id, true)
            }

            override fun onDecreased(dishItem: DishItem) {
                viewModel.changeDishItemQuantity(dishItem.id, false)

            }
        })
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.cartState.collectLatest{
                    adapter.submitList(it)
                    val cartSum = it.sumOf { dish ->
                        dish.quantity*dish.price
                    }
                    binding.buttonBuy.text = getString(R.string.buy, cartSum)
                }
            }
        }

    }

 /*   override fun <T> onSucceed(element: T) {
        val dishList = element as List<DishItem>
        adapter.submitList(dishList)
    }

    override fun onErrored(e: Exception) {
        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onPending() {
        Toast.makeText(context, "BIBA", Toast.LENGTH_SHORT).show()
    }
*/
}