package com.example.marketpulse.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.marketpulse.ui.MarketPulseTopAppBar
import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.marketpulse.ui.screens.authentication.LoginScreenPreview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
){
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        //Aquí podrías manejar la respuesta si fuera necesario
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val hasPermission = viewModel.checkNotificationPermission()
            if (!hasPermission) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        Log.i("launched_effect", "Se ejecutó el Launched Effect")
    }

    HomeScreenContent(
        modifier = modifier.fillMaxSize(),
        onBackClick = { navController.popBackStack() },
        canNavigateBack = navController.previousBackStackEntry != null,
    ) {
        viewModel.signOut()
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    canNavigateBack: Boolean,
    onSignOutClick: () -> Unit = {}
){
    Scaffold(
        topBar = {
            MarketPulseTopAppBar(
                canNavigateBack = canNavigateBack,
                onBackClick = onBackClick,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home Screen")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onSignOutClick) {
                Text(text = "Sign Out")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenContentPreview(){
    HomeScreenContent(
        modifier = Modifier.fillMaxSize(),
        onBackClick = {},
        canNavigateBack = false,
    ) {}
}
