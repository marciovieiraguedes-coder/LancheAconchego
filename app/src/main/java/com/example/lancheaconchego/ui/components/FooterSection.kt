package com.example.lancheaconchego.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lancheaconchego.data.SnackDataSource
import com.example.lancheaconchego.ui.theme.AmberGold
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.TerracottaLight
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmBrown
import com.example.lancheaconchego.ui.theme.WarmLinen
import com.example.lancheaconchego.ui.theme.WhatsAppGreen

@Composable
fun FooterSection(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(EspressoDark)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Logo & Slogan
        LogoComposable(
            iconSize = 44.dp,
            showSlogan = true,
            isDarkTheme = true
        )

        Text(
            text = "Nascido no coração do bairro São José Operário, em Manaus, com receitas artesanais preparadas com muito carinho, ingredientes frescos e aquele aconchego que você só encontra aqui.",
            fontSize = 12.sp,
            color = AmberGoldLight.copy(alpha = 0.85f),
            lineHeight = 17.sp
        )

        // Stylized Manaus 5km Delivery Radar Canvas Graphic
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2E1708)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF5C3316))
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Map,
                            contentDescription = null,
                            tint = AmberGoldLight,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Mapa de Atendimento (Raio 5km)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(TerracottaPrimary)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "MANAUS - AM",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Canvas showing radar circles and store pin
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF231105)),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.matchParentSize()) {
                        val center = Offset(size.width / 2f, size.height / 2f)
                        val maxRadius = size.height * 0.42f

                        // Outer 5km circle
                        drawCircle(
                            color = Color(0xFFC25E38).copy(alpha = 0.2f),
                            radius = maxRadius,
                            center = center
                        )
                        drawCircle(
                            color = Color(0xFFC25E38).copy(alpha = 0.5f),
                            radius = maxRadius,
                            center = center,
                            style = Stroke(
                                width = 2f,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f)
                            )
                        )

                        // 2.5km inner circle
                        drawCircle(
                            color = Color(0xFFFDE68A).copy(alpha = 0.35f),
                            radius = maxRadius * 0.55f,
                            center = center,
                            style = Stroke(width = 1.5f)
                        )

                        // Center store dot
                        drawCircle(
                            color = Color(0xFFC25E38),
                            radius = 7f,
                            center = center
                        )
                        drawCircle(
                            color = Color.White,
                            radius = 3.5f,
                            center = center
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Text(
                            text = "📍 Lanche Aconchego",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "São José • Zumbi • Tancredo • Armando Mendes • J. Teixeira",
                            fontSize = 9.sp,
                            color = AmberGoldLight.copy(alpha = 0.9f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SnackDataSource.GOOGLE_MAPS_URL))
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TerracottaPrimary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Ver Rota no Google Maps",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Store Details List
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // Address
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = TerracottaLight,
                    modifier = Modifier.size(16.dp)
                )
                Column {
                    Text(
                        text = "Endereço Oficial:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${SnackDataSource.STORE_ADDRESS_OFFICIAL} (Ref: ${SnackDataSource.STORE_REFERENCE})",
                        fontSize = 11.sp,
                        color = AmberGoldLight.copy(alpha = 0.85f)
                    )
                }
            }

            // Hours
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    tint = AmberGoldLight,
                    modifier = Modifier.size(16.dp)
                )
                Column {
                    Text(
                        text = "Horário de Funcionamento:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = SnackDataSource.OPENING_HOURS,
                        fontSize = 11.sp,
                        color = AmberGoldLight.copy(alpha = 0.85f)
                    )
                }
            }

            // WhatsApp
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.clickable {
                    val url = "https://wa.me/${SnackDataSource.WHATSAPP_PHONE_NUMBER}"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null,
                    tint = WhatsAppGreen,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "WhatsApp: ${SnackDataSource.WHATSAPP_DISPLAY_NUMBER}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = WhatsAppGreen
                )
            }
        }

        // Social Media Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Instagram
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.1f))
                    .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SnackDataSource.INSTAGRAM_URL))
                        context.startActivity(intent)
                    }
                    .padding(vertical = 8.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "📸", fontSize = 14.sp)
                    Text(
                        text = "@${SnackDataSource.INSTAGRAM_HANDLE}",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Facebook
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.1f))
                    .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SnackDataSource.FACEBOOK_URL))
                        context.startActivity(intent)
                    }
                    .padding(vertical = 8.dp, horizontal = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "👍", fontSize = 14.sp)
                    Text(
                        text = SnackDataSource.FACEBOOK_HANDLE,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        HorizontalDivider(color = Color.White.copy(alpha = 0.15f))

        // Copyright
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "© 2025 Lanche Aconchego. Manaus - AM",
                fontSize = 10.sp,
                color = AmberGoldLight.copy(alpha = 0.6f)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = "Feito com",
                    fontSize = 10.sp,
                    color = AmberGoldLight.copy(alpha = 0.6f)
                )
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = TerracottaPrimary,
                    modifier = Modifier.size(10.dp)
                )
                Text(
                    text = "em Manaus",
                    fontSize = 10.sp,
                    color = AmberGoldLight.copy(alpha = 0.6f)
                )
            }
        }
    }
}
