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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lancheaconchego.ui.theme.AmberGold
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.CreamBackground
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.TerracottaDark
import com.example.lancheaconchego.ui.theme.TerracottaLight
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmLinen

@Composable
fun TopNavBar(
    cartCount: Int,
    cartTotalFormatted: String,
    onOpenCart: () -> Unit,
    onScrollToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = CreamBackground,
        shadowElevation = 3.dp
    ) {
        Column {
            // Micro-announcement top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(EspressoDark, Color(0xFF6B3E1E), EspressoDark)
                        )
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(TerracottaLight)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "DELIVERY ABERTO",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = AmberGoldLight,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Manaus • São José Operário",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = AmberGoldLight,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = AmberGoldLight,
                        modifier = Modifier.size(11.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "18:00 às 23:30",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Main Nav Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LogoComposable(
                    iconSize = 36.dp,
                    showSlogan = false
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Cart Button with Counter Badge
                    Box(
                        modifier = Modifier
                            .testTag("header_cart_button")
                            .clip(RoundedCornerShape(14.dp))
                            .background(WarmLinen)
                            .border(1.dp, WarmBorder, RoundedCornerShape(14.dp))
                            .clickable { onOpenCart() }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box {
                                Icon(
                                    imageVector = Icons.Default.ShoppingBag,
                                    contentDescription = "Carrinho de compras",
                                    tint = TerracottaPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                                if (cartCount > 0) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .size(15.dp)
                                            .clip(CircleShape)
                                            .background(TerracottaPrimary),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "$cartCount",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                }
                            }

                            Column {
                                Text(
                                    text = "SEU PEDIDO",
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MutedBrown
                                )
                                Text(
                                    text = if (cartCount > 0) cartTotalFormatted else "R$ 0,00",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = EspressoDark
                                )
                            }
                        }
                    }

                    // "Pedir agora" Button
                    Button(
                        onClick = onScrollToMenu,
                        modifier = Modifier
                            .testTag("header_order_now_button")
                            .height(36.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TerracottaPrimary,
                            contentColor = CardSurface
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Pedir agora",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
