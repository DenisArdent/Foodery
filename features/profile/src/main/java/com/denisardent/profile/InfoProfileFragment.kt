package com.denisardent.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.denisardent.domain.NavDestination
import com.denisardent.domain.NavRouter
import com.denisardent.presentation.BaseFragment
import com.denisardent.profile.databinding.FragmentInfoProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InfoProfileFragment: BaseFragment(R.layout.fragment_info_profile) {

    private val viewModel: InfoProfileViewModel by viewModels()

    @Inject
    lateinit var navRouter: NavRouter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInfoProfileBinding.bind(view)

        binding.logOutButton.setOnClickListener {
            viewModel.logOut()

            navRouter.navigateToWithPopUp(NavDestination.SIGN_IN, NavDestination.TABS, true)
        }
    }

    override fun <T> onSucceed(state: T) {
        TODO("Not yet implemented")
    }

    override fun onErrored(e: Exception) {
        TODO("Not yet implemented")
    }

    override fun onPending() {
        TODO("Not yet implemented")
    }
}
