package com.example.lancheaconchego.ui.components

import android.content.Context
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.example.lancheaconchego.model.CartItem
import com.example.lancheaconchego.model.CustomerOrderData
import com.example.lancheaconchego.model.NeighborhoodDelivery
import com.example.lancheaconchego.model.PaymentMethod
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.CreamBackground
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartCheckoutBottomSheet(
    cartItems: Map<String, CartItem>,
    subtotal: Double,
    deliveryFee: Double,
    grandTotal: Double,
    selectedNeighborhood: NeighborhoodDelivery,
    customerOrderData: CustomerOrderData,
    onDismiss: () -> Unit,
    onUpdateQuantity: (String, Int) -> Unit,
    onRemoveItem: (String) -> Unit,
    onClearCart: () -> Unit,
    onSelectNeighborhood: (NeighborhoodDelivery) -> Unit,
    onSelectPaymentMethod: (PaymentMethod) -> Unit,
    onUpdateCustomerName: (String) -> Unit,
    onUpdateCustomerPhone: (String) -> Unit,
    onUpdateCustomerAddress: (String) -> Unit,
    onUpdateCustomerReference: (String) -> Unit,
    onUpdateNeedsChange: (Boolean, String) -> Unit,
    onSubmitOrder: (Context) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
) {
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = CreamBackground,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            // Sheet Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(EspressoDark)
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(TerracottaPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingBag,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Column {
                        Text(
                            text = "Seu Pedido Aconchego",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "${cartItems.values.sumOf { it.quantity }} itens no carrinho",
                            fontSize = 11.sp,
                            color = AmberGoldLight
                        )
                    }
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Fechar",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                if (cartItems.isEmpty()) {
                    // Empty state
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = CardSurface),
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorder)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "🛒", fontSize = 36.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Seu carrinho está vazio",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = EspressoDark
                            )
                            Text(
                                text = "Escolha suas esfirras, pastéis e bebidas favoritas no cardápio.",
                                fontSize = 12.sp,
                                color = WarmBrown,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                } else {
                    // Items List Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "ITENS SELECIONADOS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MutedBrown
                        )

                        Text(
                            text = "Limpar tudo",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = TerracottaPrimary,
                            modifier = Modifier
                                .clickable { onClearCart() }
                                .padding(4.dp)
                        )
                    }

                    // Items list
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        cartItems.values.forEach { item ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(containerColor = CardSurface),
                                border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorder)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = item.product.name,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = EspressoDark
                                        )
                                        Text(
                                            text = "R$ " + String.format("%.2f", item.product.price).replace('.', ',') + " un.",
                                            fontSize = 11.sp,
                                            color = MutedBrown
                                        )
                                        if (item.notes.isNotBlank()) {
                                            Text(
                                                text = "Obs: ${item.notes}",
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Medium,
                                                color = TerracottaPrimary
                                            )
                                        }
                                    }

                                    // Stepper
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(26.dp)
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(WarmLinen)
                                                .clickable { onUpdateQuantity(item.product.id, -1) },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Remove,
                                                contentDescription = "Diminuir",
                                                tint = EspressoDark,
                                                modifier = Modifier.size(12.dp)
                                            )
                                        }

                                        Text(
                                            text = "${item.quantity}",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = EspressoDark,
                                            modifier = Modifier.padding(horizontal = 4.dp)
                                        )

                                        Box(
                                            modifier = Modifier
                                                .size(26.dp)
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(TerracottaPrimary)
                                                .clickable { onUpdateQuantity(item.product.id, 1) },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Aumentar",
                                                tint = Color.White,
                                                modifier = Modifier.size(12.dp)
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(6.dp))

                                        Text(
                                            text = "R$ " + String.format("%.2f", item.product.price * item.quantity).replace('.', ','),
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Black,
                                            color = TerracottaPrimary
                                        )
                                    }
                                }
                            }
                        }
                    }

                    HorizontalDivider(color = WarmBorder)

                    // Delivery Info Inputs
                    Text(
                        text = "DADOS DE ENTREGA (MANAUS - 5KM)",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MutedBrown
                    )

                    OutlinedTextField(
                        value = customerOrderData.customerName,
                        onValueChange = onUpdateCustomerName,
                        label = { Text("Seu Nome") },
                        placeholder = { Text("Ex: Carlos Silva") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null, tint = MutedBrown, modifier = Modifier.size(18.dp))
                        },
                        modifier = Modifier.fillMaxWidth().testTag("checkout_input_name"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = TerracottaPrimary,
                            unfocusedBorderColor = WarmBorderStrong,
                            focusedContainerColor = CardSurface,
                            unfocusedContainerColor = CardSurface
                        )
                    )

                    OutlinedTextField(
                        value = customerOrderData.phone,
                        onValueChange = onUpdateCustomerPhone,
                        label = { Text("WhatsApp para contato") },
                        placeholder = { Text("Ex: (92) 99999-9999") },
                        leadingIcon = {
                            Icon(Icons.Default.Phone, contentDescription = null, tint = MutedBrown, modifier = Modifier.size(18.dp))
                        },
                        modifier = Modifier.fillMaxWidth().testTag("checkout_input_phone"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = TerracottaPrimary,
                            unfocusedBorderColor = WarmBorderStrong,
                            focusedContainerColor = CardSurface,
                            unfocusedContainerColor = CardSurface
                        )
                    )

                    // Neighborhood Picker
                    Text(
                        text = "Bairro / Região de Entrega:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = EspressoDark
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        SnackDataSource.NEIGHBORHOODS.forEach { zone ->
                            val isSelected = zone.name == selectedNeighborhood.name
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (isSelected) WarmLinen else CardSurface)
                                    .border(
                                        1.dp,
                                        if (isSelected) TerracottaPrimary else WarmBorder,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable { onSelectNeighborhood(zone) }
                                    .padding(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(16.dp)
                                                .clip(CircleShape)
                                                .border(2.dp, if (isSelected) TerracottaPrimary else WarmBorderStrong, CircleShape)
                                                .background(if (isSelected) TerracottaPrimary else Color.Transparent)
                                        )
                                        Text(
                                            text = zone.name,
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = EspressoDark
                                        )
                                    }

                                    Text(
                                        text = "+ R$ " + String.format("%.2f", zone.fee).replace('.', ','),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TerracottaPrimary
                                    )
                                }
                            }
                        }
                    }

                    OutlinedTextField(
                        value = customerOrderData.address,
                        onValueChange = onUpdateCustomerAddress,
                        label = { Text("Endereço Completo") },
                        placeholder = { Text("Rua, Número, Bloco/Apto") },
                        leadingIcon = {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = MutedBrown, modifier = Modifier.size(18.dp))
                        },
                        modifier = Modifier.fillMaxWidth().testTag("checkout_input_address"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = TerracottaPrimary,
                            unfocusedBorderColor = WarmBorderStrong,
                            focusedContainerColor = CardSurface,
                            unfocusedContainerColor = CardSurface
                        )
                    )

                    OutlinedTextField(
                        value = customerOrderData.referencePoint,
                        onValueChange = onUpdateCustomerReference,
                        label = { Text("Ponto de Referência") },
                        placeholder = { Text("Ex: Próximo à praça / padaria") },
                        modifier = Modifier.fillMaxWidth().testTag("checkout_input_reference"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = TerracottaPrimary,
                            unfocusedBorderColor = WarmBorderStrong,
                            focusedContainerColor = CardSurface,
                            unfocusedContainerColor = CardSurface
                        )
                    )

                    HorizontalDivider(color = WarmBorder)

                    // Payment Method Options
                    Text(
                        text = "FORMA DE PAGAMENTO",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MutedBrown
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PaymentMethod.entries.forEach { method ->
                            val isSelected = customerOrderData.paymentMethod == method
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isSelected) TerracottaPrimary else CardSurface)
                                    .border(
                                        1.dp,
                                        if (isSelected) TerracottaPrimary else WarmBorder,
                                        RoundedCornerShape(12.dp)
                                    )
                                    .clickable { onSelectPaymentMethod(method) }
                                    .padding(vertical = 10.dp, horizontal = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = when (method) {
                                            PaymentMethod.PIX -> Icons.Default.QrCode
                                            else -> Icons.Default.CreditCard
                                        },
                                        contentDescription = null,
                                        tint = if (isSelected) Color.White else EspressoDark,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = when (method) {
                                            PaymentMethod.PIX -> "PIX"
                                            PaymentMethod.DEBITO -> "Débito"
                                            PaymentMethod.CREDITO -> "Crédito"
                                        },
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else EspressoDark
                                    )
                                }
                            }
                        }
                    }

                    // Troco Switch for cash/in-person
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(CardSurface)
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Precisa de troco em dinheiro?",
                            fontSize = 12.sp,
                            color = EspressoDark
                        )
                        Switch(
                            checked = customerOrderData.needsChange,
                            onCheckedChange = { onUpdateNeedsChange(it, customerOrderData.changeAmount) },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = TerracottaPrimary)
                        )
                    }

                    if (customerOrderData.needsChange) {
                        OutlinedTextField(
                            value = customerOrderData.changeAmount,
                            onValueChange = { onUpdateNeedsChange(true, it) },
                            label = { Text("Troco para quanto?") },
                            placeholder = { Text("Ex: 50,00") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = TerracottaPrimary,
                                unfocusedBorderColor = WarmBorderStrong,
                                focusedContainerColor = CardSurface,
                                unfocusedContainerColor = CardSurface
                            )
                        )
                    }

                    HorizontalDivider(color = WarmBorder)

                    // Breakdown Summary
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = WarmLinen),
                        shape = RoundedCornerShape(14.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorder)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Subtotal itens:", fontSize = 12.sp, color = WarmBrown)
                                Text(
                                    text = "R$ " + String.format("%.2f", subtotal).replace('.', ','),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = EspressoDark
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Taxa de entrega (${selectedNeighborhood.name}):",
                                    fontSize = 12.sp,
                                    color = WarmBrown
                                )
                                Text(
                                    text = "R$ " + String.format("%.2f", deliveryFee).replace('.', ','),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TerracottaPrimary
                                )
                            }

                            HorizontalDivider(color = WarmBorderStrong)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "TOTAL GERAL:",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Black,
                                    color = EspressoDark
                                )
                                Text(
                                    text = "R$ " + String.format("%.2f", grandTotal).replace('.', ','),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Black,
                                    color = TerracottaPrimary
                                )
                            }
                        }
                    }

                    // Green WhatsApp Button CTA
                    Button(
                        onClick = { onSubmitOrder(context) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("checkout_submit_whatsapp_button"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "💬", fontSize = 18.sp)
                            Text(
                                text = "Finalizar Pedido via WhatsApp",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
