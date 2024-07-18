package com.example.profiles.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorWithRetryButton(onClick: () -> Unit, modifier: Modifier, message: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onClick,
        ) {
            Text(text = "Retry", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
@Preview
fun ErrorWithRetryButtonPreview() {
    ErrorWithRetryButton(
        onClick = {},
        modifier = Modifier,
        message = "An error occurred, try again later."
    )
}