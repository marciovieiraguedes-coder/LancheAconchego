package com.example.lancheaconchego.model

enum class ProductCategory(val label: String, val icon: String) {
    SALGADOS("Salgados", "🥟"),
    DOCES("Doces", "🍫"),
    BEBIDAS("Bebidas", "🥤"),
    SOBREMESAS("Sobremesas", "🍮")
}

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val startingFrom: Boolean = false,
    val category: ProductCategory,
    val image: Any, // Res ID or URL String
    val badge: String? = null,
    val popular: Boolean = false,
    val vegetarian: Boolean = false,
    val highlight: Boolean = false,
    val serves: String? = null
)

data class CartItem(
    val product: Product,
    val quantity: Int = 1,
    val notes: String = ""
)

enum class PaymentMethod(val label: String, val subtitle: String, val isPosMachine: Boolean) {
    PIX("PIX Instantâneo", "Chave QR / Envio de comprovante", false),
    DEBITO("Cartão de Débito", "Maquininha na entrega", true),
    CREDITO("Cartão de Crédito", "Maquininha na entrega", true)
}

data class NeighborhoodDelivery(
    val name: String,
    val distanceKm: Double,
    val fee: Double,
    val timeEstimate: String
)

data class CustomerOrderData(
    val customerName: String = "",
    val phone: String = "",
    val address: String = "",
    val neighborhood: String = "",
    val referencePoint: String = "",
    val paymentMethod: PaymentMethod = PaymentMethod.PIX,
    val needsChange: Boolean = false,
    val changeAmount: String = "",
    val generalNotes: String = ""
)

data class FeaturedCarouselProduct(
    val id: String,
    val name: String,
    val headline: String,
    val description: String,
    val startingPrice: Double,
    val priceLabel: String,
    val image: Any,
    val badge: String,
    val categoryTarget: ProductCategory
)
