package com.denisardent.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.common.entities.DishItem
import com.denisardent.domain.usecases.ChangeDishQuantityUseCase
import com.denisardent.domain.usecases.DeleteDishFromCartUseCase
import com.denisardent.domain.usecases.GetCurrentIdUseCase
import com.denisardent.domain.usecases.GetDishesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCurrentIdUseCase: GetCurrentIdUseCase,
    private val getDishesListUseCase: GetDishesListUseCase,
    private val changeDishQuantityUseCase: ChangeDishQuantityUseCase,
    private val deleteDishFromCartUseCase: DeleteDishFromCartUseCase
): ViewModel() {

    private val _cartState: MutableStateFlow<List<DishItem>> = MutableStateFlow(emptyList())
    val cartState: StateFlow<List<DishItem>> = _cartState.asStateFlow()

    init {
        viewModelScope.launch {
            getDishesListUseCase(getCurrentIdUseCase()).collectLatest {
                _cartState.tryEmit(it)
            }
        }
    }

    fun changeDishItemQuantity(dishId: Int, increasing: Boolean){
        viewModelScope.launch {
            changeDishQuantityUseCase(getCurrentIdUseCase(), dishId, increasing)
        }
    }

    fun deleteItemFromCart(dishId: Int){
        viewModelScope.launch {
            deleteDishFromCartUseCase(getCurrentIdUseCase(), dishId)
        }
    }
}