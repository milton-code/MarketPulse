package com.example.marketpulse.ui.screens.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.marketpulse.R
import com.example.marketpulse.ui.navigation.NavigationDestination


@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val viewModel: OnboardingViewModel = hiltViewModel()
    val pageList = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    val pagerState = rememberPagerState(pageCount = { 3 })
    Column(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier.weight(7f),
            state = pagerState
        ) { pageIndex ->
            HorizontalPagerContent(page = pageList[pageIndex])
        }
        HorizontalPagerIndicator(
            modifier = Modifier.weight(1f),
            indicatorSize = pageList.size,
            currentPage = pagerState.currentPage
        )
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (pagerState.currentPage == pageList.size - 1) {
                Button(
                    onClick = {
                        viewModel.saveOnboardingState(true)
                        navController.popBackStack()
                        navController.navigate("auth_graph")
                    },
                ) {
                    Text(text = stringResource(R.string.onboarding_finish))
                }
            }
        }

    }
}

@Composable
fun HorizontalPagerContent(page: OnboardingPage) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
    ) {
        if (page == OnboardingPage.First) {
            Image(
                painter = painterResource(id = page.image),
                modifier = Modifier
                    .size(125.dp)
                    .padding(16.dp)
                    .background(color = Color(0xFF000000), shape = CircleShape),
                contentDescription = null
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Image(
                    painter = painterResource(id = page.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(page.title),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                stringResource(page.description),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun HorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    indicatorSize: Int,
    currentPage: Int
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement
            .spacedBy(30.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(indicatorSize) {
            val color = if (it == currentPage) Color.Black else Color.Black.copy(0.3f)
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(color = color, shape = CircleShape)
            )
        }
    }
}

/*
Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = page.image),
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
                .background(color = Color(0xFF000000), shape = CircleShape),
            contentDescription = null
        )
        Text(text = stringResource(page.title))
        Text(stringResource(page.description),
            textAlign = TextAlign.Center)
    }
*/

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    //OnBoardingScreen(modifier = Modifier.fillMaxSize())
}

