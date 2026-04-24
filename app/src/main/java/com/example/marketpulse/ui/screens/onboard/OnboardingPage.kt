package com.example.marketpulse.ui.screens.onboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.marketpulse.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
) {
    object First : OnboardingPage(
        image = R.drawable.outline_currency_bitcoin_24,
        title = R.string.onboarding_first_page_title,
        description = R.string.onboarding_first_page_description
    )
    object Second : OnboardingPage(
        image = R.drawable.business_plan,
        title = R.string.onboarding_second_page_title,
        description = R.string.onboarding_second_page_description
    )
    object Third : OnboardingPage(
        image = R.drawable.call_service,
        title = R.string.onboarding_third_page_title,
        description = R.string.onboarding_third_page_description
    )

}
