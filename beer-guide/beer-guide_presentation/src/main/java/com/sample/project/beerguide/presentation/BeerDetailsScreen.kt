package com.sample.project.beerguide.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sample.project.beerguide.domain.model.Beer
import com.sample.project.beerguide.presentation.viewmodel.SharedBeerViewModel
import com.sample.project.beerguide.presentation.viewmodel.SharedBeerViewModel.BeerDetailsUiState
import com.sample.project.core.R
import com.sample.project.core_ui.components.*
import com.sample.project.core_ui.theme.*

@Composable
fun BeerDetailsRoute(
    onNavigateUp: () -> Unit,
    beerId: Long,
    viewModel: SharedBeerViewModel
) {
    BackHandler {
        onNavigateUp.invoke()
    }
    val beerDetailsUiState by viewModel.beerDetailsUiState.collectAsState()
    LaunchedEffect(key1 = beerId) {
        viewModel.getBeerDetailsById(beerId)
    }
    BeerDetailsScreen(
        onNavigateUp = onNavigateUp,
        beerDetailsUiState = beerDetailsUiState
    )
}

@Composable
internal fun BeerDetailsScreen(
    onNavigateUp: () -> Unit,
    beerDetailsUiState: BeerDetailsUiState
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.screenBackgroundColor)
    ) {
        when (beerDetailsUiState) {
            is BeerDetailsUiState.Loading -> {
                CircularProgress()
            }
            is BeerDetailsUiState.Success -> {
                val beerItem = beerDetailsUiState.beer
                TopAppBarDefault(
                    toolbarTitle = beerItem.name,
                    onNavigateUp = onNavigateUp
                )
                Card(
                    shape = RoundedCornerShape(spacing.spaceSmall),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = spacing.spaceMedium),
                    elevation = spacing.spaceSmall,
                    backgroundColor = MaterialTheme.colors.cardBeerItemBackgroundColor
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(beerDetailsUiState.beer.imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = spacing.spaceMedium, end = spacing.spaceMedium)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextBody(
                            stringId = R.string.beer_abv,
                            formatArgs = arrayOf(beerItem.abv.toString()),
                            modifier = Modifier.padding(
                                top = spacing.spaceSmall,
                                bottom = spacing.spaceSmall
                            )
                        )
                        if (beerItem.isBeerStrong()) {
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
                    TextBody(
                        text = beerItem.description,
                        modifier = Modifier.padding(top = spacing.spaceSmall)
                    )
                    if (beerItem.foodPairing.isNotEmpty()) {
                        TextBody(
                            stringId = R.string.beer_pairing_notes,
                            formatArgs = arrayOf(beerItem.foodPairing.joinToString()),
                            modifier = Modifier.padding(top = spacing.spaceSmall)
                        )
                    }
                }
            }
            else -> {}
        }
        if (beerDetailsUiState is BeerDetailsUiState.Error) {
            Toast.makeText(context, beerDetailsUiState.error.toString(), Toast.LENGTH_LONG).show()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
internal fun PhotoDetailsScreenPreview() {
    BeerExplorerAppTheme {
        BeerDetailsScreen(
            onNavigateUp = {},
            BeerDetailsUiState.Success(
                beer = Beer(
                    id = 1L,
                    imageUrl = "",
                    name = "Beer name",
                    description = "Beer description",
                    tagline = "Tagline",
                    abv = 55.2,
                    foodPairing = listOf("Dish 1", "Dish 2", "Dish 3")
                )
            )
        )
    }
}
