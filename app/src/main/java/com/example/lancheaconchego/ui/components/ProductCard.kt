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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lancheaconchego.model.CartItem
import com.example.lancheaconchego.model.Product
import com.example.lancheaconchego.ui.theme.AmberGold
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmBorderStrong
import com.example.lancheaconchego.ui.theme.WarmBrown
import com.example.lancheaconchego.ui.theme.WarmLinen

@Composable
fun ProductCard(
    product: Product,
    cartItem: CartItem?,
    isNotesExpanded: Boolean,
    onToggleCheckbox: (Boolean) -> Unit,
    onUpdateQuantity: (Int) -> Unit,
    onUpdateNotes: (String) -> Unit,
    onToggleNotes: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isChecked = cartItem != null && cartItem.quantity > 0
    val quantity = cartItem?.quantity ?: 0
    val notes = cartItem?.notes ?: ""

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("product_card_${product.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isChecked) Color(0xFFFFF9F3) else CardSurface
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = if (isChecked) 2.dp else 1.dp,
            color = if (isChecked) TerracottaPrimary else WarmBorder
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isChecked) 3.dp else 1.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Top Row: Custom Checkbox & Category + Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Interactive Checkbox Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onToggleCheckbox(!isChecked) }
                        .padding(vertical = 2.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isChecked) TerracottaPrimary else CardSurface)
                            .border(
                                2.dp,
                                if (isChecked) TerracottaPrimary else WarmBorderStrong,
                                RoundedCornerShape(6.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isChecked) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = product.category.label.uppercase(),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MutedBrown
                    )
                }

                // Badge pill
                if (product.badge != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(AmberGoldLight)
                            .border(1.dp, AmberGold.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = product.badge,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = EspressoDark
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Body: Details & Image Thumbnail
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = product.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = EspressoDark,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = product.description,
                        fontSize = 11.sp,
                        color = WarmBrown,
                        lineHeight = 15.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Image Box
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(WarmLinen)
                        .border(1.dp, WarmBorder, RoundedCornerShape(14.dp))
                ) {
                    AsyncImage(
                        model = product.image,
                        contentDescription = product.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Bottom Bar: Price & Quantity Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (product.startingFrom) "A partir de" else "Valor un.",
                        fontSize = 9.sp,
                        color = MutedBrown
                    )
                    Text(
                        text = "R$ " + String.format("%.2f", product.price).replace('.', ','),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        color = TerracottaPrimary
                    )
                }

                // Quantity / Add Actions
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (isChecked) {
                        // Stepper (- / count / +)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(WarmLinen)
                                .border(1.dp, WarmBorderStrong, RoundedCornerShape(12.dp))
                                .padding(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(CardSurface)
                                    .clickable { onUpdateQuantity(-1) },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "Diminuir",
                                    tint = EspressoDark,
                                    modifier = Modifier.size(14.dp)
                                )
                            }

                            Text(
                                text = "$quantity",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = EspressoDark,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(TerracottaPrimary)
                                    .clickable { onUpdateQuantity(1) },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Aumentar",
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }

                        // Observation Note Toggle Button
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (notes.isNotBlank()) TerracottaPrimary else CardSurface)
                                .border(1.dp, if (notes.isNotBlank()) TerracottaPrimary else WarmBorderStrong, RoundedCornerShape(10.dp))
                                .clickable { onToggleNotes() },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ChatBubbleOutline,
                                contentDescription = "Observação",
                                tint = if (notes.isNotBlank()) Color.White else MutedBrown,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    } else {
                        // Quick Add Button
                        Button(
                            onClick = { onToggleCheckbox(true) },
                            modifier = Modifier.height(34.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WarmLinen,
                                contentColor = EspressoDark
                            ),
                            shape = RoundedCornerShape(10.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorderStrong)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = TerracottaPrimary,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Adicionar",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Inline Observation Note Field if expanded or has notes
            if (isChecked && (isNotesExpanded || notes.isNotBlank())) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = notes,
                    onValueChange = onUpdateNotes,
                    placeholder = {
                        Text(
                            text = "Ex: sem cebola, bem quentinha",
                            fontSize = 11.sp,
                            color = MutedBrown.copy(alpha = 0.7f)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = TerracottaPrimary,
                        unfocusedBorderColor = WarmBorderStrong,
                        focusedContainerColor = CardSurface,
                        unfocusedContainerColor = CardSurface
                    )
                )
            }
        }
    }
}
