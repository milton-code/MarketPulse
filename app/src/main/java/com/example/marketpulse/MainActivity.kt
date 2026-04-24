package com.example.marketpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marketpulse.ui.MarketPulseApp
import com.example.marketpulse.ui.navigation.SplashViewModel
import com.example.marketpulse.ui.theme.MarketPulseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            splashViewModel.startDestination.value == null
        }
        enableEdgeToEdge()
        setContent {
            MarketPulseTheme {
                val startDestination by splashViewModel.startDestination

                if(startDestination != null){
                    MarketPulseApp(startDestination=startDestination!!)
                }
            }
        }
    }
}
/*
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            splashViewModel.startDestination.value == null
        }
        enableEdgeToEdge()
        setContent {
            MarketPulseTheme {
                val startDestination by splashViewModel.startDestination
                    .collectAsStateWithLifecycle()
                if(startDestination != null){
                    MarketPulseApp(startDestination=startDestination!!)
                }
            }
        }
    }
}
*/


