package com.denisardent.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.denisardent.common.ErrorResult
import com.denisardent.common.SuccessResult
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment(layoutId: Int): Fragment(layoutId) {
    fun <T> handleResult(viewModelFlow: ResultFlow<T>){
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModelFlow.collectLatest {result->
                    when (result){
                        is SuccessResult -> {
                            onSucceed(result.data)
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

    abstract fun <T> onSucceed(state: T)

    abstract fun onErrored(e: Exception)

    abstract fun onPending()
}