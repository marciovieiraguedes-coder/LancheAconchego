import React from 'react';
import { ShoppingBag, Clock, MapPin } from 'lucide-react';
import { Logo } from './Logo';

interface NavbarProps {
  cartCount: number;
  cartTotalFormatted: string;
  onOpenCart: () => void;
  onScrollToMenu: () => void;
}

export const Navbar: React.FC<NavbarProps> = ({
  cartCount,
  cartTotalFormatted,
  onOpenCart,
  onScrollToMenu
}) => {
  return (
    <header className="sticky top-0 z-40 bg-[#FFFBF5]/95 backdrop-blur-md border-b border-[#F4DEC9] shadow-sm">
      {/* Micro-announcement banner */}
      <div className="bg-[#4A2810] text-[#FDE68A] text-xs px-4 py-1.5 flex flex-wrap items-center justify-between gap-2">
        <div className="flex items-center gap-2">
          <span className="bg-[#E07A5F] text-white text-[10px] font-bold px-2 py-0.5 rounded-full uppercase tracking-wider">
            Delivery Aberto
          </span>
          <span className="flex items-center gap-1 text-[11px] text-[#FDE68A]/90">
            <MapPin className="w-3 h-3 text-[#FDE68A]" />
            Manaus • São José Operário (Raio 5km)
          </span>
        </div>
        <div className="flex items-center gap-1.5 text-[11px] font-medium text-white/90">
          <Clock className="w-3 h-3 text-[#FDE68A]" />
          <span>Terça a Domingo: 18:00 às 23:30</span>
        </div>
      </div>

      {/* Main Bar */}
      <div className="max-w-6xl mx-auto px-4 py-3 flex items-center justify-between gap-4">
        <Logo size="md" />

        <div className="flex items-center gap-3">
          {/* Cart Pill */}
          <button
            onClick={onOpenCart}
            className="flex items-center gap-2.5 bg-[#FAF0E6] hover:bg-[#F4DEC9] border border-[#F4DEC9] px-3.5 py-2 rounded-2xl transition-all shadow-sm group cursor-pointer"
          >
            <div className="relative">
              <ShoppingBag className="w-5 h-5 text-[#C25E38] transition-transform group-hover:scale-110" />
              {cartCount > 0 && (
                <span className="absolute -top-1.5 -right-2 bg-[#C25E38] text-white text-[10px] font-bold w-4 h-4 rounded-full flex items-center justify-center animate-pulse">
                  {cartCount}
                </span>
              )}
            </div>
            <div className="text-left hidden sm:block">
              <p className="text-[10px] uppercase font-bold text-[#8C5D3B] leading-none">Seu Pedido</p>
              <p className="text-xs font-extrabold text-[#4A2810] leading-tight">
                {cartCount > 0 ? cartTotalFormatted : 'R$ 0,00'}
              </p>
            </div>
          </button>

          {/* Quick Order CTA */}
          <button
            onClick={onScrollToMenu}
            className="bg-[#C25E38] hover:bg-[#A84F2E] text-white font-bold text-xs sm:text-sm px-4 py-2.5 rounded-xl shadow-md transition-all active:scale-95 cursor-pointer"
          >
            Pedir agora
          </button>
        </div>
      </div>
    </header>
  );
};
