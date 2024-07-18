package com.example.profiles.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GenderBadge(isMale: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(60))
            .background(
                color = if (isMale) Color(0xFF172f41) else Color(0xFFa3395d)
            )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            text = if (isMale) "Male" else "Female",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = if (isMale) Color(0xFF4692c8) else Color(0xFFF06292)
        )
    }
}