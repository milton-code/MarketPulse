package com.example.marketpulse.ui.screens.authentication.login

import com.example.marketpulse.R

sealed class ScreenState(val title: Int, val buttonText: Int, val ask: Int, val action: Int){
    object SignUp: ScreenState(
        R.string.continue_sign_up_title,
        R.string.sign_up,
        R.string.continue_sign_up_ask,
        R.string.sign_in
    )
    object SignIn: ScreenState(
        R.string.continue_sign_in_title,
        R.string.sign_in,
        R.string.continue_sign_in_ask,
        R.string.sign_up
    )
}