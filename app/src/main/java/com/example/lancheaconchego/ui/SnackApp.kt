package com.example.lancheaconchego.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lancheaconchego.model.ProductCategory
import com.example.lancheaconchego.ui.components.CartCheckoutBottomSheet
import com.example.lancheaconchego.ui.components.DeliveryAndPaymentSection
import com.example.lancheaconchego.ui.components.FeaturedCarouselSection
import com.example.lancheaconchego.ui.components.FloatingWhatsAppFab
import com.example.lancheaconchego.ui.components.FooterSection
import com.example.lancheaconchego.ui.components.HeroSection
import com.example.lancheaconchego.ui.components.InteractiveMenuSection
import com.example.lancheaconchego.ui.components.TopNavBar
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.CreamBackground
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmBorderStrong
import com.example.lancheaconchego.ui.theme.WarmBrown
import com.example.lancheaconchego.ui.theme.WarmLinen
import com.example.lancheaconchego.ui.theme.WhatsAppGreen
import com.example.lancheaconchego.viewmodel.SnackViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackApp(
    viewModel: SnackViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CreamBackground,
        topBar = {
            TopNavBar(
                cartCount = uiState.totalItemCount,
                cartTotalFormatted = viewModel.formatPrice(uiState.subtotal),
                onOpenCart = { viewModel.openCart() },
                onScrollToMenu = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(600)
                    }
                }
            )
        },
        bottomBar = {
            // Live Real-Time Cart Bar
            AnimatedVisibility(
                visible = uiState.totalItemCount > 0,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("sticky_bottom_cart_bar"),
                    color = EspressoDark,
                    shadowElevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.clickable { viewModel.openCart() }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
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
                                    text = "${uiState.totalItemCount} itens • ${uiState.selectedNeighborhood.name.take(18)}...",
                                    fontSize = 10.sp,
                                    color = AmberGoldLight
                                )
                                Text(
                                    text = "Total: " + viewModel.formatPrice(uiState.grandTotal),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White
                                )
                            }
                        }

                        Button(
                            onClick = { viewModel.openCart() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WhatsAppGreen,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                text = "Ver Pedido",
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
        },
        floatingActionButton = {
            FloatingWhatsAppFab(
                cartCount = uiState.totalItemCount,
                isDialogVisible = uiState.isWhatsAppDialogVisible,
                onOpenDialog = { viewModel.openWhatsAppDialog() },
                onCloseDialog = { viewModel.closeWhatsAppDialog() },
                onSendMessage = { context, msg ->
                    viewModel.sendDirectWhatsAppMessage(context, msg)
                },
                modifier = Modifier.padding(bottom = if (uiState.totalItemCount > 0) 48.dp else 0.dp)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                // 1. Hero Section
                HeroSection(
                    onOrderNow = { viewModel.openCart() },
                    onViewMenu = {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(600)
                        }
                    }
                )

                // 2. Featured Carousel Section
                FeaturedCarouselSection(
                    onSelectCategory = { category ->
                        viewModel.selectCategory(category)
                        coroutineScope.launch {
                            scrollState.animateScrollTo(750)
                        }
                    }
                )

                // 3. Interactive Menu Section
                InteractiveMenuSection(
                    selectedCategory = uiState.selectedCategory,
                    searchQuery = uiState.searchQuery,
                    onlySelected = uiState.onlySelected,
                    cartItems = uiState.cartItems,
                    activeNotesProductId = uiState.activeNotesProductId,
                    onSelectCategory = { viewModel.selectCategory(it) },
                    onSearchQueryChange = { viewModel.setSearchQuery(it) },
                    onToggleOnlySelected = { viewModel.toggleOnlySelected() },
                    onToggleItemCheckbox = { prod, checked -> viewModel.toggleItemCheckbox(prod, checked) },
                    onUpdateQuantity = { id, delta -> viewModel.updateQuantity(id, delta) },
                    onUpdateNotes = { id, notes -> viewModel.updateNotes(id, notes) },
                    onToggleNotesItem = { id -> viewModel.toggleActiveNotesItem(id) }
                )

                // 4. Delivery Zone & Payment Methods Section
                DeliveryAndPaymentSection(
                    selectedNeighborhood = uiState.selectedNeighborhood,
                    onSelectNeighborhood = { viewModel.selectNeighborhood(it) },
                    onSelectPaymentAndOrder = { method ->
                        viewModel.openCart(method)
                    }
                )

                // 5. Footer Section
                FooterSection()
            }

            // Checkout Bottom Sheet
            if (uiState.isCartOpen) {
                CartCheckoutBottomSheet(
                    cartItems = uiState.cartItems,
                    subtotal = uiState.subtotal,
                    deliveryFee = uiState.deliveryFee,
                    grandTotal = uiState.grandTotal,
                    selectedNeighborhood = uiState.selectedNeighborhood,
                    customerOrderData = uiState.customerOrderData,
                    onDismiss = { viewModel.closeCart() },
                    onUpdateQuantity = { id, delta -> viewModel.updateQuantity(id, delta) },
                    onRemoveItem = { id -> viewModel.removeItem(id) },
                    onClearCart = { viewModel.clearCart() },
                    onSelectNeighborhood = { viewModel.selectNeighborhood(it) },
                    onSelectPaymentMethod = { viewModel.selectPaymentMethod(it) },
                    onUpdateCustomerName = { viewModel.updateCustomerName(it) },
                    onUpdateCustomerPhone = { viewModel.updateCustomerPhone(it) },
                    onUpdateCustomerAddress = { viewModel.updateCustomerAddress(it) },
                    onUpdateCustomerReference = { viewModel.updateCustomerReference(it) },
                    onUpdateNeedsChange = { needs, amt -> viewModel.updateNeedsChange(needs, amt) },
                    onSubmitOrder = { ctx -> viewModel.submitOrderToWhatsApp(ctx) }
                )
            }
        }
    }
}
