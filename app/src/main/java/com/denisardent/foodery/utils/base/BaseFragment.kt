package com.denisardent.foodery.utils.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.denisardent.foodery.App
import com.denisardent.foodery.model.ErrorResult
import com.denisardent.foodery.model.SuccessResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {
    fun <T> handleResult(viewModelFlow: ResultFlow<T>){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModelFlow.collectLatest {result->
                    when (result){
                        is SuccessResult -> {
                            onSuccessed(result.data)
                        }
                        is ErrorResult -> {
                            onErrored(result.exception)
                        }
                        else -> {
                            onPending()
                        }
                    }
                }
            }
        }
    }

    fun getAppContext(): App {
        return requireContext().applicationContext as App
    }

    abstract fun <T> onSuccessed(element: T)

    abstract fun onErrored(e: Exception)

    abstract fun onPending()
}