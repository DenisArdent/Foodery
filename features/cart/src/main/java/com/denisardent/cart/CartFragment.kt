package com.denisardent.cart

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteItemFromCart(adapter.getDishId(viewHolder.adapterPosition))
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val  itemView = viewHolder.itemView
                val background = ColorDrawable(Color.RED)

                background.setBounds(0, itemView.top,   itemView.left + dX.toInt(), itemView.bottom)
                background.draw(c)

                val icon = AppCompatResources.getDrawable(context!!, com.denisardent.theme.R.drawable.ic_delete)!!

                var iconLeft = 0
                var iconRight = 0

                val margin = 32
                val iconWidth = icon.intrinsicWidth
                val iconHeight = icon.intrinsicHeight
                val cellHeight = itemView.bottom - itemView.top
                println(cellHeight)
                val iconTop = itemView.top + (cellHeight - iconHeight) / 2
                val iconBottom = iconTop + iconHeight

                // Right swipe.
                iconLeft = margin
                iconRight = margin + iconWidth

                background.draw(c)
                icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                icon.draw(c)

                super.onChildDraw(c, recyclerView, viewHolder,
                    dX, dY, actionState,
                    isCurrentlyActive
                )
            }


        }).attachToRecyclerView(binding.recyclerView)

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