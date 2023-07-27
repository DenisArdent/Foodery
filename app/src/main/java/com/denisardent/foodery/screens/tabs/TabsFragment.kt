package com.denisardent.foodery.screens.tabs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.FragmentTabsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabsFragment: Fragment(R.layout.fragment_tabs) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTabsBinding.bind(view)

        //we use childFragmentManager to get navHostFragment which navigates tabs fragments.

        val navHostFragment = childFragmentManager.findFragmentById(R.id.tabs_container) as NavHostFragment
        val navController = navHostFragment.findNavController()

        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }
}