package com.example.marketpulse.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.marketpulse.ui.screens.authentication.login.EmailViewModel
import com.example.marketpulse.ui.screens.home.HomeScreen
import com.example.marketpulse.ui.screens.onboard.OnBoardingScreen
import com.example.marketpulse.ui.screens.authentication.ContinueSignInScreen
import com.example.marketpulse.ui.screens.authentication.ContinueSignUpScreen
import com.example.marketpulse.ui.screens.authentication.login.EmailScreen
import com.example.marketpulse.ui.screens.authentication.WelcomeScreen

@Composable
fun MarketPulseNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationDestination.Onboarding.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(startDestination = NavigationDestination.Welcome.route, route = "auth_graph") {
            composable(
                route = NavigationDestination.Welcome.route,
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(700)
                    )
                }
            ) {
                WelcomeScreen(
                    modifier = modifier,
                    navController = navController
                )
            }
            composable(
                route = NavigationDestination.ContinueSignUp.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(700)
                    )
                }
            ) {
                ContinueSignUpScreen(
                    modifier = modifier,
                    navController = navController
                )
            }
            composable(
                route = NavigationDestination.ContinueSignIn.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(700)
                    )
                }
                ) {
                ContinueSignInScreen(
                    modifier = modifier,
                    navController = navController
                )
            }
            composable(
                route = NavigationDestination.Email.route,
                arguments = listOf(navArgument("sign_up") { type = NavType.BoolType }),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(700)
                    )
                }
            ) {
                val viewModel: EmailViewModel = hiltViewModel()
                EmailScreen(
                    modifier = modifier,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }

        composable(route = NavigationDestination.Onboarding.route) {
            OnBoardingScreen(modifier = modifier, navController)
        }
        composable(route = NavigationDestination.Home.route) {
            HomeScreen(
                modifier = modifier,
                navController = navController
            )
        }
    }
}

/*val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("auth_graph")
                }
                val viewModel: EmailViewModel = hiltViewModel(parentEntry)*/