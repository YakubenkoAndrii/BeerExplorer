package com.sample.project.onboarding.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.Image
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.*
import com.sample.project.core_ui.components.CircularProgress
import com.sample.project.onboarding.domain.model.OnboardingItem
import com.sample.project.onboarding.presentation.viewmodel.OnboardingViewModel
import com.sample.project.onboarding.presentation.viewmodel.OnboardingViewModel.OnboardingUiState
import com.sample.project.core.R
import com.sample.project.core_ui.components.TextBody
import com.sample.project.core_ui.components.TextTitle
import com.sample.project.core_ui.theme.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun OnboardingRoute(
    navigateToBeerBeerList: () -> Unit
) {
    val viewModel = hiltViewModel<OnboardingViewModel>()
    val onboardingUiState by viewModel.onboardingUiState.collectAsStateWithLifecycle()

    OnboardingScreen(
        navigateToBeerBeerList = navigateToBeerBeerList,
        onboardingUiState = onboardingUiState,
        saveShouldShowOnboarding = viewModel::saveShouldShowOnboarding
    )
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
internal fun OnboardingScreen(
    navigateToBeerBeerList: () -> Unit,
    onboardingUiState: OnboardingUiState,
    saveShouldShowOnboarding: (Boolean) -> Unit
) {
    val pagerState = rememberPagerState()
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        when (onboardingUiState) {
            is OnboardingUiState.Loading -> {
                CircularProgress()
            }
            is OnboardingUiState.Success -> {
                HorizontalPager(
                    modifier = Modifier.weight(10f),
                    count = onboardingUiState.onboardingItems.size,
                    state = pagerState,
                    verticalAlignment = Alignment.Top
                ) { position ->
                    PagerScreen(
                        onBoardingPage = onboardingUiState.onboardingItems[position],
                        spacing = spacing
                    )
                }
                HorizontalPagerIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(1f),
                    pagerState = pagerState,
                    activeColor = MaterialTheme.colors.activeIndicatorColor,
                    inactiveColor = MaterialTheme.colors.inactiveIndicatorColor
                )
                FinishButton(
                    modifier = Modifier.weight(1f),
                    pagerState = pagerState,
                    lastPage = onboardingUiState.onboardingItems.lastIndex
                ) {
                    saveShouldShowOnboarding.invoke(false)
                    navigateToBeerBeerList.invoke()
                }
            }
            else -> {}
        }
        if (onboardingUiState is OnboardingUiState.Error) {
            Toast.makeText(context, onboardingUiState.error.toString(), Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
internal fun PagerScreen(onBoardingPage: OnboardingItem, spacing: Dimensions) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = rememberAsyncImagePainter(onBoardingPage.imageUrl),
            contentDescription = "Pager Image"
        )
        TextTitle(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            textAlign = TextAlign.Center
        )
        TextBody(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceLarge)
                .padding(top = spacing.spaceMedium),
            text = onBoardingPage.description,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
internal fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    lastPage: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == lastPage
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colors.labelColor,
                    backgroundColor = MaterialTheme.colors.finishBackgroundColor
                )
            ) {
                TextBody(
                    text = stringResource(id = R.string.finish),
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme", showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme", showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    BeerExplorerAppTheme {
        OnboardingScreen(
            navigateToBeerBeerList = {},
            onboardingUiState = OnboardingUiState.Success(
                listOf(
                    OnboardingItem(
                        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_" +
                                "4XB70NUcaqq0fvuEczWEmOB5LQ9YxUaT-Q&usqp=CAU",
                        title = "Beer Explorer",
                        description = "bla bla bla"
                    ),
                    OnboardingItem(
                        imageUrl = "",
                        title = "Beer Explorer",
                        description = "bla2 bla2 bla2"
                    )
                )
            ),
            saveShouldShowOnboarding = {}
        )
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme", showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme", showBackground = true)
@Composable
fun FinishButtonPreview() {
    BeerExplorerAppTheme {
        FinishButton(
            modifier = Modifier,
            rememberPagerState(),
            1,
            onClick = {}
        )
    }
}
