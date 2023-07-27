package com.denisardent.foodery.navigation

import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.denisardent.domain.NavDestination
import com.denisardent.domain.NavRouter
import com.denisardent.foodery.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class MainNavRouter @Inject constructor(private val navController: NavController): NavRouter {

    override fun navigateTo(
        navDestination: NavDestination,
    ) {
        if (navDestination == NavDestination.TABS){
            navController.navigate(R.id.action_signInFragment_to_tabsFragment)
        }
        navController.navigate(getNavDestination(navDestination))
    }

    override fun navigateToWithPopUp(
        navDestination: NavDestination,
        popUpDestination: NavDestination,
        inclusivePopUp: Boolean
    ) {
        navController.navigate(getNavDestination(navDestination), null, navOptions{
            popUpTo(getNavDestination(popUpDestination)){
                inclusive = inclusivePopUp
            }
        })
    }

    private fun getNavDestination(navDestination: NavDestination): Int{
        return when(navDestination){
            NavDestination.TABS -> R.id.tabsFragment
            NavDestination.SIGN_IN -> R.id.signInFragment
            else -> R.id.signUpFragment
        }
    }
}
