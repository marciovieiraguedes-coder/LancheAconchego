package com.example.lancheaconchego.ui.components

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.lancheaconchego.data.SnackDataSource
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.TerracottaDark
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmBorderStrong
import com.example.lancheaconchego.ui.theme.WarmBrown
import com.example.lancheaconchego.ui.theme.WarmLinen
import com.example.lancheaconchego.ui.theme.WhatsAppGreen
import com.example.lancheaconchego.ui.theme.WhatsAppGreenDark

@Composable
fun FloatingWhatsAppFab(
    cartCount: Int,
    isDialogVisible: Boolean,
    onOpenDialog: () -> Unit,
    onCloseDialog: () -> Unit,
    onSendMessage: (Context, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var customText by remember { mutableStateOf("") }

    Box(modifier = modifier) {
        // FAB
        FloatingActionButton(
            onClick = onOpenDialog,
            containerColor = WhatsAppGreen,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .size(58.dp)
                .shadow(8.dp, CircleShape)
                .testTag("floating_whatsapp_fab")
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = "💬", fontSize = 26.sp)
                if (cartCount > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(TerracottaPrimary)
                            .border(1.5.dp, Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$cartCount",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // WhatsApp Quick Chat Modal Dialog
        if (isDialogVisible) {
            Dialog(onDismissRequest = onCloseDialog) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = CardSurface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column {
                        // WhatsApp Header
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(WhatsAppGreenDark, WhatsAppGreen)
                                    )
                                )
                                .padding(14.dp),
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
                                        .clip(CircleShape)
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "🥟", fontSize = 18.sp)
                                }

                                Column {
                                    Text(
                                        text = "Lanche Aconchego",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Atendimento WhatsApp Manaus",
                                        fontSize = 10.sp,
                                        color = Color.White.copy(alpha = 0.85f)
                                    )
                                }
                            }

                            IconButton(
                                onClick = onCloseDialog,
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Fechar",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        // Dialog Body
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Info badge
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(WarmLinen)
                                    .border(1.dp, WarmBorder, RoundedCornerShape(10.dp))
                                    .padding(8.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = null,
                                        tint = TerracottaPrimary,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Text(
                                        text = "Rua Rio Dimiti, 26 • Campo do Bahia",
                                        fontSize = 11.sp,
                                        color = EspressoDark
                                    )
                                }
                            }

                            Text(
                                text = "Envie uma mensagem direta para tirar dúvidas ou fazer seu pedido personalizado:",
                                fontSize = 12.sp,
                                color = WarmBrown
                            )

                            // Quick Message Options
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(WarmLinen)
                                        .border(1.dp, WarmBorderStrong, RoundedCornerShape(10.dp))
                                        .clickable {
                                            customText = "Olá! Gostaria de saber o tempo de entrega para o meu bairro hoje."
                                        }
                                        .padding(6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Tempo de entrega?",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = EspressoDark
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(WarmLinen)
                                        .border(1.dp, WarmBorderStrong, RoundedCornerShape(10.dp))
                                        .clickable {
                                            customText = "Olá! Gostaria de consultar os sabores de esfirras e pastéis disponíveis."
                                        }
                                        .padding(6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Cardápio do dia",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = EspressoDark
                                    )
                                }
                            }

                            OutlinedTextField(
                                value = customText,
                                onValueChange = { customText = it },
                                placeholder = {
                                    Text(
                                        text = "Digite sua mensagem aqui...",
                                        fontSize = 12.sp,
                                        color = MutedBrown
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                minLines = 2,
                                maxLines = 4,
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = WhatsAppGreen,
                                    unfocusedBorderColor = WarmBorderStrong,
                                    focusedContainerColor = CardSurface,
                                    unfocusedContainerColor = CardSurface
                                )
                            )

                            Button(
                                onClick = {
                                    onSendMessage(context, customText)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = WhatsAppGreen,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Send,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "Abrir Conversa no WhatsApp",
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
