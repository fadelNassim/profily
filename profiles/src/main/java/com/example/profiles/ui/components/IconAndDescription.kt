package com.example.profiles.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IconAndDescription(icon: ImageVector, description: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            shape = RoundedCornerShape(50),
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
            )
        ) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .width(16.dp),
                imageVector = icon,
                contentDescription = description,
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@Preview
fun IconAndDescriptionPreview() {
    IconAndDescription(
        icon = Icons.Default.Face,
        description = "Artist"
    )
}