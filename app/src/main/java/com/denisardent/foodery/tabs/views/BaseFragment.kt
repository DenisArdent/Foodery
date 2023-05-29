package com.denisardent.foodery.tabs.views

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.denisardent.foodery.App

open class BaseFragment(layoutId: Int): Fragment(layoutId) {
    fun getAppContext(): App {
        return requireContext().applicationContext as App
    }
}