package com.example.lancheaconchego.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sparkles
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lancheaconchego.data.SnackDataSource
import com.example.lancheaconchego.model.CartItem
import com.example.lancheaconchego.model.Product
import com.example.lancheaconchego.model.ProductCategory
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.CardSurface
import com.example.lancheaconchego.ui.theme.CreamBackground
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.TerracottaLight
import com.example.lancheaconchego.ui.theme.TerracottaPrimary
import com.example.lancheaconchego.ui.theme.WarmBorder
import com.example.lancheaconchego.ui.theme.WarmBorderStrong
import com.example.lancheaconchego.ui.theme.WarmBrown
import com.example.lancheaconchego.ui.theme.WarmLinen

@Composable
fun InteractiveMenuSection(
    selectedCategory: ProductCategory?,
    searchQuery: String,
    onlySelected: Boolean,
    cartItems: Map<String, CartItem>,
    activeNotesProductId: String?,
    onSelectCategory: (ProductCategory?) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onToggleOnlySelected: () -> Unit,
    onToggleItemCheckbox: (Product, Boolean) -> Unit,
    onUpdateQuantity: (String, Int) -> Unit,
    onUpdateNotes: (String, String) -> Unit,
    onToggleNotesItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Filter products
    val allProducts = SnackDataSource.PRODUCTS
    val filteredProducts = allProducts.filter { product ->
        // Category check
        if (selectedCategory != null && product.category != selectedCategory) {
            return@filter false
        }
        // Search check
        if (searchQuery.isNotBlank()) {
            val q = searchQuery.trim().lowercase()
            val matchesName = product.name.lowercase().contains(q)
            val matchesDesc = product.description.lowercase().contains(q)
            if (!matchesName && !matchesDesc) return@filter false
        }
        // Only selected check
        if (onlySelected && !cartItems.containsKey(product.id)) {
            return@filter false
        }
        true
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(CreamBackground)
            .padding(14.dp)
    ) {
        // Section Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Sparkles,
                contentDescription = null,
                tint = TerracottaPrimary,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "CARDÁPIO INTERATIVO",
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TerracottaPrimary
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "Monte seu Pedido no Aconchego",
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = EspressoDark
        )

        Text(
            text = "Marque os itens desejados, personalize quantidades e veja a soma em tempo real.",
            fontSize = 12.sp,
            color = WarmBrown
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = {
                Text(
                    text = "Buscar esfirra, pastel, refrigerante...",
                    fontSize = 12.sp,
                    color = MutedBrown
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MutedBrown,
                    modifier = Modifier.size(18.dp)
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Limpar",
                            tint = MutedBrown,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("menu_search_input"),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TerracottaPrimary,
                unfocusedBorderColor = WarmBorderStrong,
                focusedContainerColor = CardSurface,
                unfocusedContainerColor = CardSurface
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Category Tabs Horizontal Scroll
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // "Todos" Pill
            val isTodosSelected = selectedCategory == null
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (isTodosSelected) TerracottaPrimary else CardSurface)
                    .border(
                        1.dp,
                        if (isTodosSelected) TerracottaPrimary else WarmBorder,
                        RoundedCornerShape(14.dp)
                    )
                    .clickable { onSelectCategory(null) }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "🍽️", fontSize = 12.sp)
                    Text(
                        text = "Todos",
                        fontSize = 12.sp,
                        fontWeight = if (isTodosSelected) FontWeight.Bold else FontWeight.Medium,
                        color = if (isTodosSelected) Color.White else EspressoDark
                    )
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (isTodosSelected) Color.White.copy(alpha = 0.25f) else WarmLinen)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${allProducts.size}",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isTodosSelected) Color.White else MutedBrown
                        )
                    }
                }
            }

            // Categories
            ProductCategory.entries.forEach { category ->
                val isSelected = selectedCategory == category
                val count = allProducts.count { it.category == category }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isSelected) TerracottaPrimary else CardSurface)
                        .border(
                            1.dp,
                            if (isSelected) TerracottaPrimary else WarmBorder,
                            RoundedCornerShape(14.dp)
                        )
                        .clickable { onSelectCategory(category) }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = category.icon, fontSize = 12.sp)
                        Text(
                            text = category.label,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else EspressoDark
                        )
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(if (isSelected) Color.White.copy(alpha = 0.25f) else WarmLinen)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "$count",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else MutedBrown
                            )
                        }
                    }
                }
            }
        }

        // "Apenas selecionados" quick toggle if items in cart
        if (cartItems.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (onlySelected) EspressoDark else WarmLinen)
                    .border(
                        1.dp,
                        if (onlySelected) EspressoDark else WarmBorderStrong,
                        RoundedCornerShape(10.dp)
                    )
                    .clickable { onToggleOnlySelected() }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = null,
                    tint = if (onlySelected) AmberGoldLight else EspressoDark,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "Ver apenas selecionados (${cartItems.size})",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (onlySelected) AmberGoldLight else EspressoDark
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Product Cards List
        if (filteredProducts.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurface),
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, WarmBorder)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "🔍", fontSize = 32.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Nenhum item encontrado",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = EspressoDark
                    )
                    Text(
                        text = "Tente buscar por outro termo ou escolha outra categoria.",
                        fontSize = 11.sp,
                        color = WarmBrown,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                filteredProducts.forEach { product ->
                    ProductCard(
                        product = product,
                        cartItem = cartItems[product.id],
                        isNotesExpanded = activeNotesProductId == product.id,
                        onToggleCheckbox = { checked -> onToggleItemCheckbox(product, checked) },
                        onUpdateQuantity = { delta -> onUpdateQuantity(product.id, delta) },
                        onUpdateNotes = { notes -> onUpdateNotes(product.id, notes) },
                        onToggleNotes = { onToggleNotesItem(product.id) }
                    )
                }
            }
        }
    }
}
