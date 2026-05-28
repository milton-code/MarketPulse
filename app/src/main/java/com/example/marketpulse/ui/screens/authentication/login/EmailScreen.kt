package com.example.marketpulse.ui.screens.authentication.login

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.marketpulse.R
import com.example.marketpulse.ui.MarketPulseTopAppBar
import com.example.marketpulse.ui.navigation.NavigationDestination
import com.example.marketpulse.utils.Resource


@Composable
fun EmailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: EmailViewModel
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val imeInsets = WindowInsets.ime
    val imeVisible by remember {
        derivedStateOf { imeInsets.getBottom(density) > 0 }
    }

    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val emailState by viewModel.userEmail.collectAsStateWithLifecycle()
    val passwordState by viewModel.userPassword.collectAsStateWithLifecycle()
    var passwordVisibility by remember { mutableStateOf(false) }
    var textFieldState by remember { mutableStateOf(true) }

    val primaryButtonState by viewModel.primaryButtonState.collectAsStateWithLifecycle()


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
        val bottomPadding by animateDpAsState(
            targetValue = if (imeVisible) 0.dp else innerPadding.calculateBottomPadding()
        )
        val adjustedPadding = PaddingValues(
            start = innerPadding.calculateStartPadding(layoutDirection),
            top = innerPadding.calculateTopPadding(),
            end = innerPadding.calculateEndPadding(layoutDirection),
            bottom = bottomPadding
        )
        Column(
            modifier = modifier
                .padding(adjustedPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .animateContentSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            //Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .animateContentSize()
                    .then(if (!imeVisible) Modifier.weight(1f) else Modifier),

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
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(screenState.title),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            //Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //Email
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailState,
                    enabled = textFieldState,
                    onValueChange = {
                        viewModel.setUserEmail(it)
                    },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(16.dp))
                //Password
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordState,
                    enabled = textFieldState,
                    onValueChange = {
                        viewModel.setUserPassword(it)
                    },
                    label = { Text("Password") },
                    visualTransformation = if (passwordVisibility) {
                        VisualTransformation.None // Muestra el texto tal cual
                    } else {
                        PasswordVisualTransformation() //Lo transforma en puntos automáticamente
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                contentDescription = null
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password) // Optimiza el teclado
                )
                Spacer(modifier = Modifier.height(16.dp))
                when (val currentButtonState = primaryButtonState) {
                    is Resource.Success -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(NavigationDestination.Home.route) {
                                popUpTo(NavigationDestination.Welcome.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        textFieldState = true
                        Button(
                            modifier = Modifier.height(40.dp),
                            onClick = {
                                viewModel.onPrimaryButtonClick()
                            }
                        ) {
                            Text(stringResource(screenState.buttonText))
                        }
                        Toast.makeText(context, currentButtonState.message, Toast.LENGTH_SHORT).show()
                        viewModel.resetPrimaryButtonState()
                    }
                    is Resource.Loading -> {
                        textFieldState = false
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            color = Color.Gray
                        )
                    }
                    is Resource.Idle -> {
                        Button(
                            modifier = Modifier.height(40.dp),
                            onClick = {
                                viewModel.onPrimaryButtonClick()
                            }
                        ) {
                            Text(stringResource(screenState.buttonText))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(screenState.ask),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(//tambien pude haber utilizado textbutton
                    text = stringResource(screenState.action),
                    modifier = Modifier.clickable(
                        enabled = textFieldState,
                        onClick = {
                            if (screenState.action == R.string.sign_in) {
                                navController.navigate(NavigationDestination.ContinueSignIn.route) {
                                    popUpTo(NavigationDestination.Welcome.route) {
                                        inclusive = false
                                    }
                                }
                            } else {
                                navController.navigate(NavigationDestination.ContinueSignUp.route) {
                                    popUpTo(NavigationDestination.Welcome.route) {
                                        inclusive = false
                                    }
                                }
                            }
                        }
                    ),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun EmailScreenPreview() {
    //EmailScreen(Modifier.fillMaxSize())
}