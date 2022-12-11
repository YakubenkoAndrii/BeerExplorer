package com.sample.project.core_ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * BeerExplorer Progress bar. Part of Material - [CircularProgressIndicator] for
 * representing loading view.
 */
@Composable
fun CircularProgress() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview
internal fun CircularProgressPreview() {
    CircularProgress()
}
