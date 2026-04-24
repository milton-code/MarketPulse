package com.example.marketpulse.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.marketpulse.ui.MarketPulseTopAppBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    Scaffold(
        topBar = {
            MarketPulseTopAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Home Screen")
        }
    }
}