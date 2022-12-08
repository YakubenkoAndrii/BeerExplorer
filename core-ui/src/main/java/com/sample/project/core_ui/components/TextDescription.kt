package com.sample.project.core_ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sample.project.core_ui.theme.labelColor

@Composable
fun TextDescription(
    text: String = "",
    stringId: Int = 0,
    vararg formatArgs: Any,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier
) {
    Text(
        text = text.ifEmpty { stringResource(id = stringId, *formatArgs) },
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.labelColor,
        modifier = modifier,
        textAlign = textAlign
    )
}

@Composable
@Preview
internal fun TextDescriptionPreview() {
    Text(
        "Text description"
    )
}
