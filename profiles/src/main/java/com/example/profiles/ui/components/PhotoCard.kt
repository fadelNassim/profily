package com.example.profiles.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.profiles.presentation.displaymodels.ProfileUi

@Composable
fun ProfileCard(
    profile: ProfileUi
) {
    var isExpanded by remember { mutableStateOf(false) }
    val arrowRotationDegrees by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val profilePictureScale by animateFloatAsState(
        targetValue = if (isExpanded) 0.8f else 1f,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Card(
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp,
            )
            .animateContentSize()
            .clickable { isExpanded = !isExpanded },
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = profile.picture,
                    contentDescription = null,
                    modifier = Modifier
                        .height(64.dp)
                        .width(64.dp)
                        .scale(profilePictureScale)
                        .clip(RoundedCornerShape(50))
                )
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row {
                        Text(
                            text = profile.name.uppercase(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )
                        GenderBadge(
                            isMale = profile.isMale
                        )

                    }
                    Text(
                        text = profile.country.uppercase(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                    Column {
                        AnimatedVisibility(
                            visible = isExpanded,
                            enter = expandVertically(),
                            exit = shrinkVertically()
                        ) {
                            Column(modifier = Modifier.padding(top = 8.dp)) {
                                IconAndDescription(
                                    icon = Icons.Default.DateRange,
                                    description = profile.birthDate
                                )
                                IconAndDescription(
                                    icon = Icons.Default.Email,
                                    description = profile.email
                                )
                                IconAndDescription(
                                    icon = Icons.Default.Phone,
                                    description = profile.phone
                                )
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0x11000000)),
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .rotate(arrowRotationDegrees)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilesCardPreview() {
    ProfileCard(
        profile = ProfileUi(
            name = "John Doe",
            phone = "1234567890",
            picture = "https://randomuser.me/api/",
            country = "USA",
            email = "some@email.com",
            birthDate = "1992",
            isMale = true,
            id = 1
        )
    )
}