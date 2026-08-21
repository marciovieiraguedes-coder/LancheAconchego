package com.example.lancheaconchego.ui.components

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lancheaconchego.R
import com.example.lancheaconchego.data.SnackDataSource
import com.example.lancheaconchego.ui.theme.AmberGold
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.SuccessGreen
import com.example.lancheaconchego.ui.theme.TerracottaLight
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmBorderStrong
import com.example.lancheaconchego.ui.theme.WarmBrown
import com.example.lancheaconchego.ui.theme.WarmLinen

@Composable
fun HeroSection(
    onOrderNow: () -> Unit,
    onViewMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFBF5),
                        Color(0xFFFFF3E3),
                        Color(0xFFFFFBF5)
                    )
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Friendly Pill
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(WarmLinen)
                .border(1.dp, WarmBorder, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(TerracottaLight)
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = TerracottaPrimary,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = "Feito com amor, quentinho e artesanal",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = MutedBrown
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Headline
        Text(
            text = "O Sabor do Aconchego direto na sua casa",
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            color = EspressoDark,
            lineHeight = 30.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Massa artesanal fofinha, pastéis crocantes sequinhos e esfirras recheadas com tempero de família. Saindo quentinho de São José Operário para toda a Zona Leste de Manaus.",
            fontSize = 13.sp,
            color = WarmBrown,
            lineHeight = 18.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Price Anchor Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Esfirras
            Card(
                modifier = Modifier
                    .weight(1f)
                    .testTag("hero_price_card_esfirras"),
                colors = CardDefaults.cardColors(containerColor = WarmLinen),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorder)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(TerracottaPrimary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🥟", fontSize = 18.sp)
                    }
                    Column {
                        Text(
                            text = "ESFIRRAS",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = MutedBrown
                        )
                        Text(
                            text = "A partir de",
                            fontSize = 10.sp,
                            color = WarmBrown
                        )
                        Text(
                            text = "R$ 5,90",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = TerracottaPrimary
                        )
                    }
                }
            }

            // Pasteis
            Card(
                modifier = Modifier
                    .weight(1f)
                    .testTag("hero_price_card_pasteis"),
                colors = CardDefaults.cardColors(containerColor = WarmLinen),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorder)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(AmberGold.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🥐", fontSize = 18.sp)
                    }
                    Column {
                        Text(
                            text = "PASTÉIS",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = MutedBrown
                        )
                        Text(
                            text = "A partir de",
                            fontSize = 10.sp,
                            color = WarmBrown
                        )
                        Text(
                            text = "R$ 8,50",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = TerracottaPrimary
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // CTA Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = onOrderNow,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("hero_button_order_now"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TerracottaPrimary,
                    contentColor = CardSurface
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "Pedir agora",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }

            OutlinedButton(
                onClick = onViewMenu,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("hero_button_view_menu"),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = WarmLinen,
                    contentColor = EspressoDark
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorderStrong),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.RestaurantMenu,
                    contentDescription = null,
                    tint = TerracottaPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Cardápio",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Visual Showcase Banner Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = R.drawable.esfirras_destaque,
                    contentDescription = "Esfirras Artesanais do Lanche Aconchego",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                // Dark gradient overlay
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    EspressoDark.copy(alpha = 0.85f)
                                )
                            )
                        )
                )

                // Banner Details text
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(TerracottaLight)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "RECEITAS CASEIRAS",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Esfirras & Pastéis com Sabor de Casa",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Ingredientes frescos e massa que derrete na boca",
                        fontSize = 11.sp,
                        color = AmberGoldLight
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Info Badges Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CardSurface)
                .border(1.dp, WarmBorder, RoundedCornerShape(12.dp))
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SnackDataSource.GOOGLE_MAPS_URL))
                    context.startActivity(intent)
                }
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = TerracottaPrimary,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "Raio de 5km (Campo do Bahia, Manaus)",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = EspressoDark
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = AmberGold,
                    modifier = Modifier.size(12.dp)
                )
                Text(
                    text = "4.9 ★ (+1.200 pedidos)",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = TerracottaPrimary
                )
            }
        }
    }
}
