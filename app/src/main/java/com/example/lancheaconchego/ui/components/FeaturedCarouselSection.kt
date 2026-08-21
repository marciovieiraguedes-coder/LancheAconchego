package com.example.lancheaconchego.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Sparkles
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lancheaconchego.data.SnackDataSource
import com.example.lancheaconchego.model.ProductCategory
import com.example.lancheaconchego.ui.theme.AmberGold
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.TerracottaLight
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmBorderStrong
import com.example.lancheaconchego.ui.theme.WarmBrown
import com.example.lancheaconchego.ui.theme.WarmLinen

@Composable
fun FeaturedCarouselSection(
    onSelectCategory: (ProductCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = SnackDataSource.FEATURED_CAROUSEL_PRODUCTS
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentItem = items[currentIndex]

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(WarmLinen.copy(alpha = 0.6f))
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Sparkles,
                        contentDescription = null,
                        tint = TerracottaPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "DESTAQUES DA CASA",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TerracottaPrimary
                    )
                }
                Text(
                    text = "Principais Sabores do Aconchego",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = EspressoDark
                )
            }

            // Carousel Arrow Controls
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                IconButton(
                    onClick = {
                        currentIndex = (currentIndex - 1 + items.size) % items.size
                    },
                    modifier = Modifier.size(32.dp),
                    colors = IconButtonDefaults.iconButtonColors(containerColor = CardSurface)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Anterior",
                        tint = EspressoDark,
                        modifier = Modifier.size(18.dp)
                    )
                }
                IconButton(
                    onClick = {
                        currentIndex = (currentIndex + 1) % items.size
                    },
                    modifier = Modifier.size(32.dp),
                    colors = IconButtonDefaults.iconButtonColors(containerColor = CardSurface)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Próximo",
                        tint = EspressoDark,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Main Featured Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("featured_carousel_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column {
                // Image Box with badges
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                ) {
                    AsyncImage(
                        model = currentItem.image,
                        contentDescription = currentItem.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Badge top-left
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(EspressoDark.copy(alpha = 0.85f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = currentItem.badge,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = AmberGoldLight
                        )
                    }

                    // Price bottom-right
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(TerracottaPrimary)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = currentItem.priceLabel,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }

                // Information & Category Action Button
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = currentItem.headline.uppercase(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TerracottaPrimary
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = currentItem.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = EspressoDark
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = currentItem.description,
                        fontSize = 12.sp,
                        color = WarmBrown,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Preço inicial especial",
                                fontSize = 10.sp,
                                color = MutedBrown
                            )
                            Text(
                                text = "R$ " + String.format("%.2f", currentItem.startingPrice).replace('.', ','),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = TerracottaPrimary
                            )
                        }

                        Button(
                            onClick = { onSelectCategory(currentItem.categoryTarget) },
                            colors = ButtonDefaults.buttonColors(containerColor = TerracottaPrimary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Ver Opções",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Thumbnail selector strip
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = index == currentIndex
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) CardSurface else WarmLinen)
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) TerracottaPrimary else WarmBorder,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { currentIndex = index }
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = when (item.categoryTarget) {
                                ProductCategory.SALGADOS -> if (index == 0) "Esfirras" else "Pastéis"
                                ProductCategory.DOCES -> "Doces"
                                ProductCategory.BEBIDAS -> "Bebidas"
                                else -> "Snacks"
                            },
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                            color = if (isSelected) TerracottaPrimary else EspressoDark,
                            maxLines = 1
                        )
                        Text(
                            text = "R$ " + String.format("%.2f", item.startingPrice).replace('.', ','),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = MutedBrown
                        )
                    }
                }
            }
        }
    }
}
