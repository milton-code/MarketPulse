package com.example.marketpulse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marketpulse.core.AppState
import com.example.marketpulse.ui.MainViewModel
import com.example.marketpulse.ui.MarketPulseApp
import com.example.marketpulse.ui.navigation.NavigationDestination
import com.example.marketpulse.ui.theme.MarketPulseTheme
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            mainViewModel.appState.value is AppState.Loading
        }
        
        enableEdgeToEdge()
        setContent {
            MarketPulseTheme {
                val appState by mainViewModel.appState.collectAsStateWithLifecycle()

                when (appState) {
                    is AppState.Loading -> { /* Se mantiene la SplashScreen */ }
                    is AppState.OnboardingRequired -> {
                        MarketPulseApp(startDestination = NavigationDestination.Onboarding.route)
                    }
                    is AppState.Authenticated -> {
                        MarketPulseApp(startDestination = NavigationDestination.Home.route)
                    }
                    is AppState.Unauthenticated -> {
                        MarketPulseApp(startDestination = NavigationDestination.AuthGraph.route)
                    }
                }
            }
        }
    }
}
