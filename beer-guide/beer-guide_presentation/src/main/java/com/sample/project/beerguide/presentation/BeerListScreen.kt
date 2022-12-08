package com.sample.project.beerguide.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sample.project.beerguide.domain.model.Beer
import com.sample.project.beerguide.presentation.viewmodel.SharedBeerViewModel
import com.sample.project.beerguide.presentation.viewmodel.SharedBeerViewModel.BeerGuideUiState
import com.sample.project.core.R
import com.sample.project.core_ui.components.CircularProgress
import com.sample.project.core_ui.components.TextDescription
import com.sample.project.core_ui.components.TextTitle
import com.sample.project.core_ui.components.TopAppBarDefault
import com.sample.project.core_ui.theme.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun BeerListRoute(
    onNavigateUp: () -> Unit,
    navigateToBeerDetails: (Long) -> Unit,
    viewModel: SharedBeerViewModel
) {
    BackHandler {
        onNavigateUp.invoke()
    }
    val beerGuideUiState by viewModel.beerGuideUiState.collectAsStateWithLifecycle()
    BeerListScreen(
        navigateToBeerDetails = navigateToBeerDetails,
        onNavigateUp = onNavigateUp,
        beerGuideUiState = beerGuideUiState
    )
}

@Composable
internal fun BeerListScreen(
    navigateToBeerDetails: (Long) -> Unit,
    onNavigateUp: () -> Unit,
    beerGuideUiState: BeerGuideUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.screenBackgroundColor)
    ) {
        TopAppBarDefault(onNavigateUp, "BeerExplorer")
        BeerList(
            navigateToBeerDetails,
            beerGuideUiState
        )
    }
}

@Composable
internal fun BeerList(
    navigateToBeerDetails: (Long) -> Unit,
    beerGuideUiState: BeerGuideUiState
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val beerListState = rememberLazyListState()

    when (beerGuideUiState) {
        is BeerGuideUiState.Loading -> {
            CircularProgress()
        }
        is BeerGuideUiState.Success -> {
            if (beerGuideUiState.beerItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    TextTitle(
                        stringId = R.string.beer_list_empty_message,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colors.titleBackgroundColor
                            )
                            .padding(
                                vertical = spacing.spaceSmall,
                                horizontal = spacing.spaceMedium
                            ),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    state = beerListState,
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(spacing.spaceMedium),
                    verticalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
                ) {
                    items(beerGuideUiState.beerItems) { beer ->
                        BeerItem(beer, navigateToBeerDetails)
                    }
                }
            }
        }
        else -> {
        }
    }
    if (beerGuideUiState is BeerGuideUiState.Error) {
        Toast.makeText(context, beerGuideUiState.error.toString(), Toast.LENGTH_LONG).show()
    }
}

@Composable
internal fun BeerItem(
    item: Beer,
    navigateToImageDetails: (Long) -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(spacing.spaceSmall),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navigateToImageDetails.invoke(item.id)
            },
        elevation = spacing.spaceSmall,
        backgroundColor = MaterialTheme.colors.cardBeerItemBackgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    remember(item.imageUrl) {
                        ImageRequest.Builder(context)
                            .data(item.imageUrl)
                            .diskCacheKey(item.imageUrl)
                            .memoryCacheKey(item.imageUrl)
                            .build()
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .width(110.dp)
                    .height(140.dp)
                    .padding(
                        top = spacing.spaceSmall,
                        bottom = spacing.spaceSmall
                    ),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = spacing.spaceSmall,
                        end = spacing.spaceMedium,
                        top = spacing.spaceMedium,
                        bottom = spacing.spaceMedium
                    )
            ) {
                TextTitle(
                    text = item.name,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextDescription(
                        stringId = R.string.beer_abv,
                        formatArgs = arrayOf(item.abv.toString()),
                        modifier = Modifier.padding(
                            top = spacing.spaceSmall,
                            bottom = spacing.spaceSmall
                        )
                    )
                    if (item.isBeerStrong()) {
                        Image(
                            modifier = Modifier
                                .padding(start = spacing.spaceMedium)
                                .width(24.dp)
                                .height(24.dp)
                                .align(Alignment.CenterVertically),
                            painter = painterResource(id = R.drawable.muscle),
                            contentDescription = null
                        )
                    }
                }
                TextDescription(
                    text = item.tagline,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
internal fun BeerItemSoftPreview() {
    BeerExplorerAppTheme {
        BeerItem(
            Beer(
                1,
                "Beer name",
                "Beer description",
                "",
                "Beer tagline",
                4.5,
                listOf("Beer1", "Beer2")
            ),
            navigateToImageDetails = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
internal fun BeerItemStrongPreview() {
    BeerExplorerAppTheme {
        BeerItem(
            Beer(
                1,
                "Beer name",
                "Beer description",
                "",
                "Beer tagline",
                5.1,
                listOf("Beer1", "Beer2")
            ),
            navigateToImageDetails = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
internal fun BeerListScreenSuccessFilledPreview() {
    BeerExplorerAppTheme {
        BeerListScreen(
            navigateToBeerDetails = {},
            onNavigateUp = {},
            beerGuideUiState = BeerGuideUiState.Success(
                mutableListOf(
                    Beer(
                        1,
                        "Beer name 1",
                        "Beer description 1",
                        "",
                        "Beer tagline 1",
                        4.0,
                        listOf("Beer1", "Beer2")
                    ),
                    Beer(
                        1,
                        "Beer name 2",
                        "Beer description 2",
                        "",
                        "Beer tagline 2",
                        5.1,
                        listOf("Beer3", "Beer4")
                    )
                )
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
internal fun BeerListScreenSuccessEmptyPreview() {
    BeerExplorerAppTheme {
        BeerListScreen(
            navigateToBeerDetails = {},
            onNavigateUp = {},
            beerGuideUiState = BeerGuideUiState.Success(mutableListOf())
        )
    }
}
