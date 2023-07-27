package com.denisardent.domain

interface NavRouter {
    fun navigateTo(navDestination: NavDestination)

    fun navigateToWithPopUp(
        navDestination: NavDestination,
        popUpDestination: NavDestination,
        inclusivePopUp: Boolean
    )
}

enum class NavDestination{
    SIGN_IN,
    SIGN_UP,
    TABS
}