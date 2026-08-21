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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lancheaconchego.data.SnackDataSource
import com.example.lancheaconchego.model.NeighborhoodDelivery
import com.example.lancheaconchego.model.PaymentMethod
import com.example.lancheaconchego.ui.theme.AmberGold
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.CreamBackground
import com.example.lancheaconchego.ui.theme.EspressoBrown
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.SuccessGreen
import com.example.lancheaconchego.ui.theme.SuccessGreenLight
import com.example.lancheaconchego.ui.theme.TerracottaDark
import com.example.lancheaconchego.ui.theme.TerracottaLight
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmBorderStrong
import com.example.lancheaconchego.ui.theme.WarmBrown
import com.example.lancheaconchego.ui.theme.WarmLinen

@Composable
fun DeliveryAndPaymentSection(
    selectedNeighborhood: NeighborhoodDelivery,
    onSelectNeighborhood: (NeighborhoodDelivery) -> Unit,
    onSelectPaymentAndOrder: (PaymentMethod) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val neighborhoods = SnackDataSource.NEIGHBORHOODS

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(CreamBackground)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section Header
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.NearMe,
                    contentDescription = null,
                    tint = TerracottaPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "ENTREGA ÁGIL & SEGURANÇA",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TerracottaPrimary
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "Entrega no Campo do Bahia e Pagamento Facilitado",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = EspressoDark
            )

            Text(
                text = "Levamos o aconchego quentinho até sua porta em um raio de até 5km a partir do nosso endereço oficial.",
                fontSize = 12.sp,
                color = WarmBrown
            )
        }

        // Delivery Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                // Header linked to maps
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(WarmLinen)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SnackDataSource.GOOGLE_MAPS_URL))
                            context.startActivity(intent)
                        }
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(TerracottaPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "Raio de Entrega: 5.0 km",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = EspressoDark
                            )
                            Text(
                                text = "Rua Rio Dimiti, 26 • São José Operário",
                                fontSize = 11.sp,
                                color = WarmBrown
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = "Abrir mapa",
                        tint = TerracottaPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "CONSULTE SEU BAIRRO / REGIÃO:",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MutedBrown
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Neighborhoods Grid
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    neighborhoods.forEach { zone ->
                        val isSelected = zone.name == selectedNeighborhood.name
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) WarmLinen else CardSurface)
                                .border(
                                    1.dp,
                                    if (isSelected) TerracottaPrimary else WarmBorder,
                                    RoundedCornerShape(12.dp)
                                )
                                .clickable { onSelectNeighborhood(zone) }
                                .padding(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = zone.name,
                                        fontSize = 12.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = EspressoDark
                                    )
                                    Text(
                                        text = "~${zone.distanceKm} km • ${zone.timeEstimate}",
                                        fontSize = 10.sp,
                                        color = MutedBrown
                                    )
                                }

                                Text(
                                    text = "R$ " + String.format("%.2f", zone.fee).replace('.', ','),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Black,
                                    color = TerracottaPrimary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Dynamic Delivery Result Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(WarmLinen)
                        .border(1.dp, WarmBorderStrong, RoundedCornerShape(14.dp))
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Região: ${selectedNeighborhood.name}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = EspressoDark
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Timer,
                                    contentDescription = null,
                                    tint = TerracottaPrimary,
                                    modifier = Modifier.size(13.dp)
                                )
                                Text(
                                    text = "Tempo estimado: ${selectedNeighborhood.timeEstimate}",
                                    fontSize = 11.sp,
                                    color = WarmBrown
                                )
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "TAXA DE ENTREGA",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = MutedBrown
                            )
                            Text(
                                text = "R$ " + String.format("%.2f", selectedNeighborhood.fee).replace('.', ','),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = TerracottaPrimary
                            )
                        }
                    }
                }
            }
        }

        // Payment Methods Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = EspressoDark
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = AmberGoldLight,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "FORMAS DE PAGAMENTO",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = AmberGoldLight
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Praticidade e Segurança",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Escolha sua forma preferida para abrir o pedido:",
                    fontSize = 12.sp,
                    color = AmberGoldLight.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Payment Method Buttons
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    // PIX
                    PaymentMethodRowCard(
                        title = "PIX Instantâneo",
                        badge = "Mais Rápido",
                        subtitle = "Chave PIX direta com confirmação rápida",
                        icon = Icons.Default.QrCode,
                        iconBg = Color(0xFF00B4D8).copy(alpha = 0.2f),
                        iconTint = Color(0xFF90E0EF),
                        onClick = { onSelectPaymentAndOrder(PaymentMethod.PIX) }
                    )

                    // Débito
                    PaymentMethodRowCard(
                        title = "Cartão de Débito",
                        badge = "Maquininha na Entrega",
                        subtitle = "O motoboy leva a maquininha sem fio até você",
                        icon = Icons.Default.CreditCard,
                        iconBg = AmberGold.copy(alpha = 0.2f),
                        iconTint = AmberGoldLight,
                        onClick = { onSelectPaymentAndOrder(PaymentMethod.DEBITO) }
                    )

                    // Crédito
                    PaymentMethodRowCard(
                        title = "Cartão de Crédito",
                        badge = "Maquininha na Entrega",
                        subtitle = "Visa, Master, Elo e principais bandeiras",
                        icon = Icons.Default.CreditCard,
                        iconBg = TerracottaLight.copy(alpha = 0.2f),
                        iconTint = Color(0xFFFFCDB2),
                        onClick = { onSelectPaymentAndOrder(PaymentMethod.CREDITO) }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Quality & Trust Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = SuccessGreenLight,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Sem taxas extras no cartão ou PIX. Pague ao receber!",
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@Composable
private fun PaymentMethodRowCard(
    title: String,
    badge: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBg: Color,
    iconTint: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.1f))
            .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(TerracottaPrimary)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = badge,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                Text(
                    text = subtitle,
                    fontSize = 10.sp,
                    color = AmberGoldLight.copy(alpha = 0.8f)
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = AmberGoldLight,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
