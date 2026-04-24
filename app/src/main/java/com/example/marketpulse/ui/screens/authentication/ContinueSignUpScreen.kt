package com.example.marketpulse.ui.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.marketpulse.R
import com.example.marketpulse.ui.MarketPulseTopAppBar
import com.example.marketpulse.ui.navigation.NavigationDestination

@Composable
fun ContinueSignUpScreen(
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
        Column(
            modifier = modifier.padding(innerPadding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.outline_currency_bitcoin_24),
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(20.dp)
                        .background(color = Color(0xFF000000), shape = CircleShape)
                )
                Text(
                    text = stringResource(R.string.continue_sign_up_title),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }


            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate( "${NavigationDestination.Email.route.substringBefore("/")}/${true}")
                    //authViewModel.setEmailState(continueState)
                }
            ) {
                Text(stringResource(R.string.continuar_con_e_mail))
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text(stringResource(R.string.continuar_con_google))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.continue_sign_up_ask),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.sign_in),
                modifier = Modifier.clickable(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(NavigationDestination.ContinueSignIn.route)
                    }
                ),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

        }
    }

}

@Composable
@Preview(showBackground = true)
fun ContinueScreenPreview(){
    //ContinueScreen(modifier = Modifier.fillMaxSize())
}