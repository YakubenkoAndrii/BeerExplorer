package com.sample.project.core_ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sample.project.core_ui.theme.topAppBarBackgroundColor
import com.sample.project.core_ui.theme.topAppBarContentColor

@Composable
fun TopAppBarDefault(
    onNavigateUp: () -> Unit,
    toolbarTitle: String
) {
    TopAppBar(
        title = {
            Text(
                text = toolbarTitle,
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            }
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
@Preview
internal fun TopBarDefaultPreview() {
    TopAppBar(
        title = {
            Text(
                text = "Toolbar title",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.topAppBarContentColor
                )
            }
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}
