package com.denisardent.foodery.screens.tabs.views.infoprofile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.FragmentInfoProfileBinding
import com.denisardent.foodery.utils.base.BaseFragment
import com.denisardent.foodery.utils.ViewModelFactory

class InfoProfileFragment: BaseFragment(R.layout.fragment_info_profile) {

    private val viewModel: InfoProfileViewModel by viewModels { ViewModelFactory(getAppContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInfoProfileBinding.bind(view)

        val parentNavHost = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val parentNavController = parentNavHost.navController

        binding.logOutButton.setOnClickListener {
            viewModel.logOut()

            parentNavController.navigate(R.id.signInFragment, null, navOptions{
                popUpTo(R.id.tabsFragment){
                    inclusive = true
                }
            })
        }
    }

    override fun <T> onSucceed(element: T) {
        TODO("Not yet implemented")
    }

    override fun onErrored(e: Exception) {
        TODO("Not yet implemented")
    }

    override fun onPending() {
        TODO("Not yet implemented")
    }
}