package com.denisardent.foodery.tabs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.FragmentTabsBinding

class TabsFragment: Fragment(R.layout.fragment_tabs) {
    val args: TabsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = args.accountId
        val binding = FragmentTabsBinding.bind(view)

        //we use childFragmentManager to get navHostFragment which navigates tabs fragments.

        val navHostFragment = childFragmentManager.findFragmentById(R.id.tabs_container) as NavHostFragment
        val navController = navHostFragment.findNavController()


        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }
}