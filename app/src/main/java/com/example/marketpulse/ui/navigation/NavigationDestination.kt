package com.example.marketpulse.ui.navigation

sealed class NavigationDestination(internal val route: String) {
    object Onboarding : NavigationDestination(route = "onboarding")
    object Welcome : NavigationDestination(route = "welcome")
    object ContinueSignIn: NavigationDestination(route = "continue_sign_in")
    object ContinueSignUp: NavigationDestination(route = "continue_sign_up")
    object Email : NavigationDestination(route = "email/{sign_up}")
    object Home : NavigationDestination(route = "home")


}


