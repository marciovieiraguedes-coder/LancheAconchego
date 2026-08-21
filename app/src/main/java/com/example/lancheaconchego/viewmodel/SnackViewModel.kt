package com.example.lancheaconchego.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.lancheaconchego.data.SnackDataSource
import com.example.lancheaconchego.model.CartItem
import com.example.lancheaconchego.model.CustomerOrderData
import com.example.lancheaconchego.model.NeighborhoodDelivery
import com.example.lancheaconchego.model.PaymentMethod
import com.example.lancheaconchego.model.Product
import com.example.lancheaconchego.model.ProductCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale

data class SnackUiState(
    val selectedCategory: ProductCategory? = null, // null means "Todos"
    val searchQuery: String = "",
    val onlySelected: Boolean = false,
    val cartItems: Map<String, CartItem> = emptyMap(),
    val selectedNeighborhood: NeighborhoodDelivery = SnackDataSource.NEIGHBORHOODS.first(),
    val customerOrderData: CustomerOrderData = CustomerOrderData(
        paymentMethod = PaymentMethod.PIX
    ),
    val isCartOpen: Boolean = false,
    val isWhatsAppDialogVisible: Boolean = false,
    val activeNotesProductId: String? = null,
    val orderSuccessMessage: String? = null
) {
    val subtotal: Double
        get() = cartItems.values.sumOf { it.product.price * it.quantity }

    val totalItemCount: Int
        get() = cartItems.values.sumOf { it.quantity }

    val deliveryFee: Double
        get() = if (cartItems.isNotEmpty()) selectedNeighborhood.fee else 0.0

    val grandTotal: Double
        get() = subtotal + deliveryFee
}

class SnackViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        SnackUiState(
            cartItems = buildInitialCart()
        )
    )
    val uiState: StateFlow<SnackUiState> = _uiState.asStateFlow()

    private fun buildInitialCart(): Map<String, CartItem> {
        val initialEsfirra = SnackDataSource.PRODUCTS.find { it.id == "sal-1" }
        val initialPastel = SnackDataSource.PRODUCTS.find { it.id == "sal-5" }
        val map = mutableMapOf<String, CartItem>()
        if (initialEsfirra != null) {
            map[initialEsfirra.id] = CartItem(product = initialEsfirra, quantity = 2, notes = "Bem quentinha")
        }
        if (initialPastel != null) {
            map[initialPastel.id] = CartItem(product = initialPastel, quantity = 1)
        }
        return map
    }

    fun selectCategory(category: ProductCategory?) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun setSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun toggleOnlySelected() {
        _uiState.update { it.copy(onlySelected = !it.onlySelected) }
    }

    fun toggleItemCheckbox(product: Product, checked: Boolean) {
        _uiState.update { state ->
            val updated = state.cartItems.toMutableMap()
            if (checked) {
                if (!updated.containsKey(product.id)) {
                    updated[product.id] = CartItem(product = product, quantity = 1)
                }
            } else {
                updated.remove(product.id)
            }
            state.copy(cartItems = updated)
        }
    }

    fun updateQuantity(productId: String, delta: Int) {
        _uiState.update { state ->
            val current = state.cartItems[productId] ?: return@update state
            val newQty = current.quantity + delta
            val updated = state.cartItems.toMutableMap()
            if (newQty > 0) {
                updated[productId] = current.copy(quantity = newQty)
            } else {
                updated.remove(productId)
            }
            state.copy(cartItems = updated)
        }
    }

    fun updateNotes(productId: String, notes: String) {
        _uiState.update { state ->
            val current = state.cartItems[productId] ?: return@update state
            val updated = state.cartItems.toMutableMap()
            updated[productId] = current.copy(notes = notes)
            state.copy(cartItems = updated)
        }
    }

    fun toggleActiveNotesItem(productId: String?) {
        _uiState.update { it.copy(activeNotesProductId = if (it.activeNotesProductId == productId) null else productId) }
    }

    fun removeItem(productId: String) {
        _uiState.update { state ->
            val updated = state.cartItems.toMutableMap()
            updated.remove(productId)
            state.copy(cartItems = updated)
        }
    }

    fun clearCart() {
        _uiState.update { it.copy(cartItems = emptyMap()) }
    }

    fun selectNeighborhood(neighborhood: NeighborhoodDelivery) {
        _uiState.update { it.copy(selectedNeighborhood = neighborhood) }
    }

    fun selectPaymentMethod(method: PaymentMethod) {
        _uiState.update { state ->
            state.copy(
                customerOrderData = state.customerOrderData.copy(paymentMethod = method)
            )
        }
    }

    fun updateCustomerName(name: String) {
        _uiState.update { state ->
            state.copy(customerOrderData = state.customerOrderData.copy(customerName = name))
        }
    }

    fun updateCustomerPhone(phone: String) {
        _uiState.update { state ->
            state.copy(customerOrderData = state.customerOrderData.copy(phone = phone))
        }
    }

    fun updateCustomerAddress(address: String) {
        _uiState.update { state ->
            state.copy(customerOrderData = state.customerOrderData.copy(address = address))
        }
    }

    fun updateCustomerReference(ref: String) {
        _uiState.update { state ->
            state.copy(customerOrderData = state.customerOrderData.copy(referencePoint = ref))
        }
    }

    fun updateNeedsChange(needs: Boolean, amount: String = "") {
        _uiState.update { state ->
            state.copy(customerOrderData = state.customerOrderData.copy(needsChange = needs, changeAmount = amount))
        }
    }

    fun openCart(paymentMethod: PaymentMethod? = null) {
        _uiState.update { state ->
            val updatedOrder = if (paymentMethod != null) {
                state.customerOrderData.copy(paymentMethod = paymentMethod)
            } else {
                state.customerOrderData
            }
            state.copy(isCartOpen = true, customerOrderData = updatedOrder)
        }
    }

    fun closeCart() {
        _uiState.update { it.copy(isCartOpen = false, orderSuccessMessage = null) }
    }

    fun openWhatsAppDialog() {
        _uiState.update { it.copy(isWhatsAppDialogVisible = true) }
    }

    fun closeWhatsAppDialog() {
        _uiState.update { it.copy(isWhatsAppDialogVisible = false) }
    }

    fun formatPrice(amount: Double): String {
        return String.format(Locale("pt", "BR"), "R$ %.2f", amount)
    }

    fun submitOrderToWhatsApp(context: Context) {
        val state = _uiState.value
        if (state.cartItems.isEmpty()) {
            Toast.makeText(context, "Adicione itens ao carrinho primeiro!", Toast.LENGTH_SHORT).show()
            return
        }

        val itemsListText = state.cartItems.values.joinToString("\n") { item ->
            val itemTotal = formatPrice(item.product.price * item.quantity)
            val notesStr = if (item.notes.isNotBlank()) " _(Obs: ${item.notes.trim()})_" else ""
            "• *${item.quantity}x* ${item.product.name} - $itemTotal$notesStr"
        }

        val payment = state.customerOrderData.paymentMethod
        val paymentLabel = when (payment) {
            PaymentMethod.PIX -> "PIX (Chave Instantânea)"
            PaymentMethod.DEBITO -> "Cartão de Débito 💳 (Levar maquininha na entrega)"
            PaymentMethod.CREDITO -> "Cartão de Crédito 💳 (Levar maquininha na entrega)"
        }

        val changeStr = if (state.customerOrderData.needsChange && state.customerOrderData.changeAmount.isNotBlank()) {
            "\n*Troco para:* R$ ${state.customerOrderData.changeAmount}"
        } else ""

        val fullMessage = """
            🥟 *NOVO PEDIDO - LANCHE ACONCHEGO*
            _Endereço Oficial: ${SnackDataSource.STORE_ADDRESS_OFFICIAL}_
            ----------------------------------------
            *Cliente:* ${state.customerOrderData.customerName.ifBlank { "Cliente Aconchego" }}
            *Telefone:* ${state.customerOrderData.phone.ifBlank { "Não informado" }}
            *Bairro/Região (Manaus - Raio 5km):* ${state.selectedNeighborhood.name}
            *Endereço Completo:* ${state.customerOrderData.address.ifBlank { "Retirada ou combinar" }}
            *Ponto de Referência:* ${state.customerOrderData.referencePoint.ifBlank { "Sem referência" }}

            *📋 ITENS DO PEDIDO:*
            $itemsListText

            ----------------------------------------
            *Subtotal:* ${formatPrice(state.subtotal)}
            *Taxa de Entrega:* ${formatPrice(state.deliveryFee)} (${state.selectedNeighborhood.name})
            *TOTAL A PAGAR:* ${formatPrice(state.grandTotal)}

            *Forma de Pagamento:* $paymentLabel$changeStr
            *Tempo de Entrega Estimado:* ${state.selectedNeighborhood.timeEstimate}
            ----------------------------------------
            _Pedido gerado via Cardápio Digital - Lanche Aconchego_
        """.trimIndent()

        launchWhatsAppIntent(context, fullMessage)
        _uiState.update { it.copy(orderSuccessMessage = "Pedido pronto! Abrindo WhatsApp...") }
    }

    fun sendDirectWhatsAppMessage(context: Context, customMessage: String) {
        val state = _uiState.value
        val message = if (customMessage.isNotBlank()) {
            customMessage
        } else if (state.totalItemCount > 0) {
            "Olá! Estou montando um pedido de ${state.totalItemCount} itens no valor de ${formatPrice(state.subtotal)} para entrega em Manaus e gostaria de atendimento."
        } else {
            "Olá, equipe do Lanche Aconchego! Gostaria de consultar o cardápio e fazer um pedido com entrega a partir do endereço oficial na ${SnackDataSource.STORE_ADDRESS_OFFICIAL}."
        }

        launchWhatsAppIntent(context, message)
        closeWhatsAppDialog()
    }

    private fun launchWhatsAppIntent(context: Context, message: String) {
        try {
            val encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString())
            val url = "https://api.whatsapp.com/send?phone=${SnackDataSource.WHATSAPP_PHONE_NUMBER}&text=$encodedMessage"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback to web link
            try {
                val encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString())
                val url = "https://wa.me/${SnackDataSource.WHATSAPP_PHONE_NUMBER}?text=$encodedMessage"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            } catch (e2: Exception) {
                Toast.makeText(context, "Não foi possível abrir o WhatsApp", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
