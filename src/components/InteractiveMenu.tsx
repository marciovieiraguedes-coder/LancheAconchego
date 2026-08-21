import React, { useState } from 'react';
import { Search, Plus, Minus, Check, MessageSquare, Filter, Sparkles, X } from 'lucide-react';
import { PRODUCTS } from '../data/products';
import { CartItem, Product, ProductCategory } from '../types';

interface InteractiveMenuProps {
  selectedCategory: ProductCategory;
  searchQuery: string;
  onlySelected: boolean;
  cartItems: Record<string, CartItem>;
  onSelectCategory: (category: ProductCategory) => void;
  onSearchQueryChange: (query: string) => void;
  onToggleOnlySelected: () => void;
  onToggleItem: (product: Product, checked: boolean) => void;
  onUpdateQuantity: (productId: string, delta: number) => void;
  onUpdateNotes: (productId: string, notes: string) => void;
}

const CATEGORIES: { label: ProductCategory; icon: string }[] = [
  { label: 'Todos', icon: '🍽️' },
  { label: 'Salgados', icon: '🥟' },
  { label: 'Doces', icon: '🍫' },
  { label: 'Bebidas', icon: '🥤' },
  { label: 'Sobremesas', icon: '🍮' },
];

export const InteractiveMenu: React.FC<InteractiveMenuProps> = ({
  selectedCategory,
  searchQuery,
  onlySelected,
  cartItems,
  onSelectCategory,
  onSearchQueryChange,
  onToggleOnlySelected,
  onToggleItem,
  onUpdateQuantity,
  onUpdateNotes,
}) => {
  const [activeNotesItem, setActiveNotesItem] = useState<string | null>(null);

  const filteredProducts = PRODUCTS.filter((product) => {
    if (selectedCategory !== 'Todos' && product.category !== selectedCategory) {
      return false;
    }
    if (searchQuery.trim()) {
      const q = searchQuery.toLowerCase().trim();
      const matchName = product.name.toLowerCase().includes(q);
      const matchDesc = product.description.toLowerCase().includes(q);
      if (!matchName && !matchDesc) return false;
    }
    if (onlySelected && !cartItems[product.id]) {
      return false;
    }
    return true;
  });

  return (
    <section id="menu-section" className="py-10 bg-[#FFFBF5]">
      <div className="max-w-6xl mx-auto px-4">
        {/* Title */}
        <div className="mb-6">
          <div className="flex items-center gap-1.5 text-xs font-extrabold text-[#C25E38] uppercase tracking-wider">
            <Sparkles className="w-3.5 h-3.5" />
            Cardápio Interativo
          </div>
          <h2 className="text-2xl sm:text-3xl font-black text-[#4A2810] mt-0.5">
            Monte seu Pedido no Aconchego
          </h2>
          <p className="text-xs sm:text-sm text-[#6B4423] mt-1">
            Marque os itens desejados, ajuste quantidades e personalize observações com cálculo imediato do total.
          </p>
        </div>

        {/* Controls: Search & Category Chips */}
        <div className="space-y-3 mb-6">
          {/* Search bar */}
          <div className="relative">
            <Search className="w-4 h-4 text-[#8C5D3B] absolute left-3.5 top-1/2 -translate-y-1/2" />
            <input
              type="text"
              value={searchQuery}
              onChange={(e) => onSearchQueryChange(e.target.value)}
              placeholder="Buscar por esfirra de carne, pastel de queijo, suco de laranja..."
              className="w-full pl-10 pr-10 py-3 bg-white border border-[#E5C3A6] focus:border-[#C25E38] focus:ring-2 focus:ring-[#C25E38]/20 rounded-2xl text-sm text-[#4A2810] placeholder-[#8C5D3B]/60 outline-none transition-all shadow-xs"
            />
            {searchQuery && (
              <button
                onClick={() => onSearchQueryChange('')}
                className="absolute right-3.5 top-1/2 -translate-y-1/2 text-[#8C5D3B] hover:text-[#4A2810]"
              >
                <X className="w-4 h-4" />
              </button>
            )}
          </div>

          {/* Category Tabs & Filter switch */}
          <div className="flex flex-wrap items-center justify-between gap-2.5">
            <div className="flex items-center gap-2 overflow-x-auto pb-1 max-w-full">
              {CATEGORIES.map((cat) => {
                const isSelected = selectedCategory === cat.label;
                const count = cat.label === 'Todos'
                  ? PRODUCTS.length
                  : PRODUCTS.filter((p) => p.category === cat.label).length;

                return (
                  <button
                    key={cat.label}
                    onClick={() => onSelectCategory(cat.label)}
                    className={`flex items-center gap-2 px-3.5 py-2 rounded-2xl text-xs font-bold transition-all whitespace-nowrap cursor-pointer ${
                      isSelected
                        ? 'bg-[#C25E38] text-white shadow-sm'
                        : 'bg-white text-[#4A2810] border border-[#F4DEC9] hover:bg-[#FAF0E6]'
                    }`}
                  >
                    <span>{cat.icon}</span>
                    <span>{cat.label}</span>
                    <span className={`text-[10px] px-1.5 py-0.5 rounded-full ${
                      isSelected ? 'bg-white/20 text-white' : 'bg-[#FAF0E6] text-[#8C5D3B]'
                    }`}>
                      {count}
                    </span>
                  </button>
                );
              })}
            </div>

            {/* Filter selected toggle */}
            {Object.keys(cartItems).length > 0 && (
              <button
                onClick={onToggleOnlySelected}
                className={`flex items-center gap-1.5 text-xs font-bold px-3 py-1.5 rounded-xl border transition-all cursor-pointer ${
                  onlySelected
                    ? 'bg-[#4A2810] text-[#FDE68A] border-[#4A2810]'
                    : 'bg-[#FAF0E6] text-[#6B4423] border-[#E5C3A6] hover:bg-white'
                }`}
              >
                <Filter className="w-3.5 h-3.5" />
                Ver apenas selecionados ({Object.keys(cartItems).length})
              </button>
            )}
          </div>
        </div>

        {/* Product Cards Grid */}
        {filteredProducts.length === 0 ? (
          <div className="bg-white rounded-3xl p-10 text-center border border-[#F4DEC9] shadow-xs">
            <span className="text-4xl">🔍</span>
            <h3 className="text-base font-bold text-[#4A2810] mt-3">Nenhum item encontrado</h3>
            <p className="text-xs text-[#6B4423] mt-1">Tente buscar por outro termo ou limpe os filtros de categoria.</p>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {filteredProducts.map((product) => {
              const itemInCart = cartItems[product.id];
              const isChecked = !!itemInCart && itemInCart.quantity > 0;
              const quantity = itemInCart?.quantity || 0;
              const notes = itemInCart?.notes || '';
              const isNotesOpen = activeNotesItem === product.id;

              return (
                <div
                  key={product.id}
                  className={`bg-white rounded-3xl p-4 border transition-all flex flex-col justify-between shadow-xs ${
                    isChecked
                      ? 'border-[#C25E38] ring-2 ring-[#C25E38]/15 bg-[#FFF9F3]'
                      : 'border-[#F4DEC9] hover:border-[#E5C3A6]'
                  }`}
                >
                  <div>
                    {/* Top Row: Category + Custom Checkbox */}
                    <div className="flex items-center justify-between gap-2 mb-2.5">
                      <label className="flex items-center gap-2 cursor-pointer select-none">
                        <div
                          className={`w-5 h-5 rounded-md flex items-center justify-center border transition-all ${
                            isChecked
                              ? 'bg-[#C25E38] border-[#C25E38] text-white'
                              : 'bg-white border-[#E5C3A6] hover:border-[#C25E38]'
                          }`}
                          onClick={() => onToggleItem(product, !isChecked)}
                        >
                          {isChecked && <Check className="w-3.5 h-3.5 stroke-[3]" />}
                        </div>
                        <span className="text-[10px] font-extrabold uppercase text-[#8C5D3B] tracking-wider">
                          {product.category}
                        </span>
                      </label>

                      {product.badge && (
                        <span className="bg-[#FDE68A] text-[#4A2810] text-[10px] font-extrabold px-2.5 py-0.5 rounded-full border border-[#D97706]/30">
                          {product.badge}
                        </span>
                      )}
                    </div>

                    {/* Image & Description */}
                    <div className="flex gap-3">
                      <div className="flex-1 min-w-0">
                        <h4 className="font-bold text-sm text-[#4A2810] leading-tight">
                          {product.name}
                        </h4>
                        <p className="text-xs text-[#6B4423] mt-1 line-clamp-2 leading-relaxed">
                          {product.description}
                        </p>
                      </div>

                      <div className="w-18 h-18 rounded-2xl overflow-hidden bg-[#FAF0E6] shrink-0 border border-[#F4DEC9]">
                        <img
                          src={product.image}
                          alt={product.name}
                          className="w-full h-full object-cover"
                        />
                      </div>
                    </div>
                  </div>

                  {/* Bottom Controls */}
                  <div className="mt-4 pt-3 border-t border-[#F4DEC9]/60 flex items-center justify-between gap-2">
                    <div>
                      <p className="text-[10px] text-[#8C5D3B]">
                        {product.startingFrom ? 'A partir de' : 'Valor un.'}
                      </p>
                      <p className="text-base font-black text-[#C25E38]">
                        R$ {product.price.toFixed(2).replace('.', ',')}
                      </p>
                    </div>

                    {isChecked ? (
                      <div className="flex items-center gap-1.5">
                        {/* Stepper */}
                        <div className="flex items-center bg-[#FAF0E6] border border-[#E5C3A6] rounded-xl p-0.5">
                          <button
                            onClick={() => onUpdateQuantity(product.id, -1)}
                            className="w-7 h-7 rounded-lg bg-white flex items-center justify-center text-[#4A2810] hover:bg-[#F4DEC9] active:scale-95 transition-all cursor-pointer"
                          >
                            <Minus className="w-3.5 h-3.5" />
                          </button>
                          <span className="w-7 text-center font-black text-xs text-[#4A2810]">
                            {quantity}
                          </span>
                          <button
                            onClick={() => onUpdateQuantity(product.id, 1)}
                            className="w-7 h-7 rounded-lg bg-[#C25E38] flex items-center justify-center text-white hover:bg-[#A84F2E] active:scale-95 transition-all cursor-pointer"
                          >
                            <Plus className="w-3.5 h-3.5" />
                          </button>
                        </div>

                        {/* Note trigger */}
                        <button
                          onClick={() => setActiveNotesItem(isNotesOpen ? null : product.id)}
                          className={`w-8 h-8 rounded-xl border flex items-center justify-center transition-colors cursor-pointer ${
                            notes
                              ? 'bg-[#C25E38] border-[#C25E38] text-white'
                              : 'bg-white border-[#E5C3A6] text-[#8C5D3B] hover:border-[#C25E38]'
                          }`}
                          title="Adicionar observação"
                        >
                          <MessageSquare className="w-3.5 h-3.5" />
                        </button>
                      </div>
                    ) : (
                      <button
                        onClick={() => onToggleItem(product, true)}
                        className="flex items-center gap-1.5 bg-[#FAF0E6] hover:bg-[#C25E38] text-[#4A2810] hover:text-white border border-[#E5C3A6] hover:border-[#C25E38] text-xs font-bold px-3.5 py-2 rounded-xl transition-all cursor-pointer shadow-xs active:scale-95"
                      >
                        <Plus className="w-3.5 h-3.5" />
                        Adicionar
                      </button>
                    )}
                  </div>

                  {/* Inline Note Input */}
                  {isChecked && (isNotesOpen || notes) && (
                    <div className="mt-2.5 pt-2 border-t border-dashed border-[#F4DEC9]">
                      <input
                        type="text"
                        value={notes}
                        onChange={(e) => onUpdateNotes(product.id, e.target.value)}
                        placeholder="Obs: sem cebola, bem quentinho..."
                        className="w-full text-xs px-3 py-1.5 bg-white border border-[#E5C3A6] focus:border-[#C25E38] rounded-xl text-[#4A2810] outline-none"
                      />
                    </div>
                  )}
                </div>
              );
            })}
          </div>
        )}
      </div>
    </section>
  );
};
