import React, { useState, useMemo } from 'react';
import { Navbar } from './components/Navbar';
import { Hero } from './components/Hero';
import { ProductCarousel } from './components/ProductCarousel';
import { InteractiveMenu } from './components/InteractiveMenu';
import { DeliveryAndPayment } from './components/DeliveryAndPayment';
import { OrderDrawer } from './components/OrderDrawer';
import { FloatingWhatsApp } from './components/FloatingWhatsApp';
import { Footer } from './components/Footer';
import { NEIGHBORHOODS, PRODUCTS } from './data/products';
import { CartItem, CustomerOrderData, NeighborhoodDelivery, PaymentMethod, Product, ProductCategory } from './types';
import { ShoppingBag, ArrowRight } from 'lucide-react';

export function App() {
  const [selectedCategory, setSelectedCategory] = useState<ProductCategory>('Todos');
  const [searchQuery, setSearchQuery] = useState('');
  const [onlySelected, setOnlySelected] = useState(false);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  // Initial cart with a warm starter selection
  const [cartItems, setCartItems] = useState<Record<string, CartItem>>(() => {
    const esfirra = PRODUCTS.find((p) => p.id === 'sal-1');
    const pastel = PRODUCTS.find((p) => p.id === 'sal-5');
    const initial: Record<string, CartItem> = {};
    if (esfirra) initial[esfirra.id] = { product: esfirra, quantity: 2, notes: 'Bem quentinha' };
    if (pastel) initial[pastel.id] = { product: pastel, quantity: 1 };
    return initial;
  });

  const [selectedNeighborhood, setSelectedNeighborhood] = useState<NeighborhoodDelivery>(NEIGHBORHOODS[0]);

  const [customerData, setCustomerData] = useState<CustomerOrderData>({
    customerName: '',
    phone: '',
    address: '',
    neighborhood: NEIGHBORHOODS[0].name,
    referencePoint: '',
    paymentMethod: 'PIX',
    needsChange: false,
    changeAmount: '',
  });

  // Calculate totals
  const subtotal = useMemo(() => {
    return (Object.values(cartItems) as CartItem[]).reduce((sum, item) => sum + item.product.price * item.quantity, 0);
  }, [cartItems]);

  const totalItemsCount = useMemo(() => {
    return (Object.values(cartItems) as CartItem[]).reduce((sum, item) => sum + item.quantity, 0);
  }, [cartItems]);

  const deliveryFee = totalItemsCount > 0 ? selectedNeighborhood.fee : 0;
  const grandTotal = subtotal + deliveryFee;

  const formattedSubtotal = `R$ ${subtotal.toFixed(2).replace('.', ',')}`;
  const formattedGrandTotal = `R$ ${grandTotal.toFixed(2).replace('.', ',')}`;

  // Cart operations
  const handleToggleItem = (product: Product, checked: boolean) => {
    setCartItems((prev) => {
      const next = { ...prev };
      if (checked) {
        if (!next[product.id]) {
          next[product.id] = { product, quantity: 1 };
        }
      } else {
        delete next[product.id];
      }
      return next;
    });
  };

  const handleUpdateQuantity = (productId: string, delta: number) => {
    setCartItems((prev) => {
      const current = prev[productId];
      if (!current) return prev;
      const next = { ...prev };
      const newQty = current.quantity + delta;
      if (newQty > 0) {
        next[productId] = { ...current, quantity: newQty };
      } else {
        delete next[productId];
      }
      return next;
    });
  };

  const handleUpdateNotes = (productId: string, notes: string) => {
    setCartItems((prev) => {
      if (!prev[productId]) return prev;
      return {
        ...prev,
        [productId]: { ...prev[productId], notes },
      };
    });
  };

  const handleRemoveItem = (productId: string) => {
    setCartItems((prev) => {
      const next = { ...prev };
      delete next[productId];
      return next;
    });
  };

  const handleClearCart = () => {
    setCartItems({});
  };

  const handleSelectPaymentAndOrder = (method: PaymentMethod) => {
    setCustomerData((prev) => ({ ...prev, paymentMethod: method }));
    setIsDrawerOpen(true);
  };

  const scrollToMenu = () => {
    const el = document.getElementById('menu-section');
    if (el) el.scrollIntoView({ behavior: 'smooth' });
  };

  return (
    <div className="min-h-screen flex flex-col bg-[#FFFBF5] text-[#4A2810]">
      {/* Top sticky navbar */}
      <Navbar
        cartCount={totalItemsCount}
        cartTotalFormatted={formattedSubtotal}
        onOpenCart={() => setIsDrawerOpen(true)}
        onScrollToMenu={scrollToMenu}
      />

      {/* Main Content */}
      <main className="flex-1">
        {/* 1. Hero Section */}
        <Hero
          onOrderNow={() => setIsDrawerOpen(true)}
          onViewMenu={scrollToMenu}
        />

        {/* 2. Featured Carousel */}
        <ProductCarousel
          onSelectCategory={(cat) => {
            setSelectedCategory(cat);
            scrollToMenu();
          }}
        />

        {/* 3. Interactive Menu */}
        <InteractiveMenu
          selectedCategory={selectedCategory}
          searchQuery={searchQuery}
          onlySelected={onlySelected}
          cartItems={cartItems}
          onSelectCategory={setSelectedCategory}
          onSearchQueryChange={setSearchQuery}
          onToggleOnlySelected={() => setOnlySelected((prev) => !prev)}
          onToggleItem={handleToggleItem}
          onUpdateQuantity={handleUpdateQuantity}
          onUpdateNotes={handleUpdateNotes}
        />

        {/* 4. Delivery Zone & Payment Methods */}
        <DeliveryAndPayment
          selectedNeighborhood={selectedNeighborhood}
          onSelectNeighborhood={setSelectedNeighborhood}
          onSelectPaymentAndOrder={handleSelectPaymentAndOrder}
        />
      </main>

      {/* Footer */}
      <Footer />

      {/* Sticky Bottom Bar (When cart has items) */}
      {totalItemsCount > 0 && (
        <div className="sticky bottom-0 z-30 bg-[#4A2810] text-white p-3.5 shadow-2xl border-t border-white/10 flex items-center justify-between gap-4 animate-in slide-in-from-bottom-2">
          <div
            onClick={() => setIsDrawerOpen(true)}
            className="flex items-center gap-3 cursor-pointer"
          >
            <div className="w-10 h-10 rounded-xl bg-[#C25E38] flex items-center justify-center text-white">
              <ShoppingBag className="w-5 h-5" />
            </div>
            <div>
              <p className="text-[11px] text-[#FDE68A]">
                {totalItemsCount} {totalItemsCount === 1 ? 'item' : 'itens'} • {selectedNeighborhood.name.split('(')[0].trim()}
              </p>
              <p className="text-sm sm:text-base font-black text-white">
                Total: {formattedGrandTotal}
              </p>
            </div>
          </div>

          <button
            onClick={() => setIsDrawerOpen(true)}
            className="bg-[#25D366] hover:bg-[#1EBE5D] text-white text-xs sm:text-sm font-black px-5 py-2.5 rounded-xl flex items-center gap-2 shadow-md transition-all active:scale-95 cursor-pointer"
          >
            <span>Ver Pedido</span>
            <ArrowRight className="w-4 h-4" />
          </button>
        </div>
      )}

      {/* WhatsApp Floating Chat */}
      <FloatingWhatsApp cartCount={totalItemsCount} />

      {/* Order & Checkout Drawer */}
      <OrderDrawer
        isOpen={isDrawerOpen}
        cartItems={cartItems}
        subtotal={subtotal}
        deliveryFee={deliveryFee}
        grandTotal={grandTotal}
        selectedNeighborhood={selectedNeighborhood}
        customerData={customerData}
        onClose={() => setIsDrawerOpen(false)}
        onUpdateQuantity={handleUpdateQuantity}
        onRemoveItem={handleRemoveItem}
        onClearCart={handleClearCart}
        onSelectNeighborhood={setSelectedNeighborhood}
        onSelectPaymentMethod={(method) => setCustomerData((prev) => ({ ...prev, paymentMethod: method }))}
        onUpdateCustomerData={(data) => setCustomerData((prev) => ({ ...prev, ...data }))}
      />
    </div>
  );
}

export default App;
