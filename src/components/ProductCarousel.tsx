import React, { useState } from 'react';
import { ChevronLeft, ChevronRight, Sparkles, ArrowRight } from 'lucide-react';
import { FEATURED_CAROUSEL_PRODUCTS } from '../data/products';
import { ProductCategory } from '../types';

interface ProductCarouselProps {
  onSelectCategory: (category: ProductCategory) => void;
}

export const ProductCarousel: React.FC<ProductCarouselProps> = ({ onSelectCategory }) => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const items = FEATURED_CAROUSEL_PRODUCTS;
  const current = items[currentIndex];

  const prev = () => setCurrentIndex((idx) => (idx === 0 ? items.length - 1 : idx - 1));
  const next = () => setCurrentIndex((idx) => (idx === items.length - 1 ? 0 : idx + 1));

  return (
    <section className="py-8 bg-[#FAF0E6]/60 border-b border-[#F4DEC9]">
      <div className="max-w-6xl mx-auto px-4">
        {/* Header */}
        <div className="flex items-center justify-between gap-4 mb-5">
          <div>
            <div className="flex items-center gap-1.5 text-xs font-extrabold text-[#C25E38] uppercase tracking-wider">
              <Sparkles className="w-3.5 h-3.5" />
              Destaques da Casa
            </div>
            <h2 className="text-xl sm:text-2xl font-black text-[#4A2810]">
              Principais Sabores do Aconchego
            </h2>
          </div>

          <div className="flex items-center gap-2">
            <button
              onClick={prev}
              aria-label="Anterior"
              className="w-9 h-9 rounded-full bg-white hover:bg-[#FAF0E6] border border-[#F4DEC9] flex items-center justify-center text-[#4A2810] shadow-sm transition-colors cursor-pointer"
            >
              <ChevronLeft className="w-5 h-5" />
            </button>
            <button
              onClick={next}
              aria-label="Próximo"
              className="w-9 h-9 rounded-full bg-white hover:bg-[#FAF0E6] border border-[#F4DEC9] flex items-center justify-center text-[#4A2810] shadow-sm transition-colors cursor-pointer"
            >
              <ChevronRight className="w-5 h-5" />
            </button>
          </div>
        </div>

        {/* Featured Card */}
        <div className="bg-white rounded-3xl overflow-hidden border border-[#F4DEC9] shadow-md grid grid-cols-1 md:grid-cols-12">
          <div className="md:col-span-6 relative h-60 md:h-auto min-h-[240px]">
            <img
              src={current.image}
              alt={current.name}
              className="w-full h-full object-cover"
            />
            <div className="absolute top-4 left-4 bg-[#4A2810]/90 backdrop-blur-xs text-[#FDE68A] text-xs font-bold px-3 py-1.5 rounded-xl">
              {current.badge}
            </div>
            <div className="absolute bottom-4 right-4 bg-[#C25E38] text-white text-xs font-black px-3.5 py-1.5 rounded-xl shadow-md">
              {current.priceLabel}
            </div>
          </div>

          <div className="md:col-span-6 p-6 sm:p-8 flex flex-col justify-between">
            <div>
              <p className="text-xs uppercase font-extrabold text-[#C25E38] tracking-wider">
                {current.headline}
              </p>
              <h3 className="text-2xl font-black text-[#4A2810] mt-1">
                {current.name}
              </h3>
              <p className="text-sm text-[#6B4423] mt-2.5 leading-relaxed">
                {current.description}
              </p>
            </div>

            <div className="mt-6 pt-5 border-t border-[#F4DEC9] flex items-center justify-between gap-4">
              <div>
                <p className="text-[11px] text-[#8C5D3B]">Preço inicial especial</p>
                <p className="text-xl font-black text-[#C25E38]">
                  R$ {current.startingPrice.toFixed(2).replace('.', ',')}
                </p>
              </div>

              <button
                onClick={() => onSelectCategory(current.categoryTarget)}
                className="flex items-center gap-2 bg-[#C25E38] hover:bg-[#A84F2E] text-white font-bold text-xs sm:text-sm px-5 py-2.5 rounded-xl transition-all cursor-pointer shadow-sm"
              >
                Ver Opções
                <ArrowRight className="w-4 h-4" />
              </button>
            </div>
          </div>
        </div>

        {/* Quick Nav Chips */}
        <div className="grid grid-cols-2 sm:grid-cols-4 gap-2.5 mt-4">
          {items.map((item, idx) => (
            <button
              key={item.id}
              onClick={() => setCurrentIndex(idx)}
              className={`p-3 rounded-2xl text-left border transition-all cursor-pointer ${
                idx === currentIndex
                  ? 'bg-white border-[#C25E38] shadow-sm ring-2 ring-[#C25E38]/20'
                  : 'bg-[#FAF0E6]/80 border-[#F4DEC9] hover:bg-white text-[#6B4423]'
              }`}
            >
              <p className={`text-xs font-bold ${idx === currentIndex ? 'text-[#C25E38]' : 'text-[#4A2810]'}`}>
                {item.name}
              </p>
              <p className="text-[11px] font-semibold text-[#8C5D3B] mt-0.5">
                R$ {item.startingPrice.toFixed(2).replace('.', ',')}
              </p>
            </button>
          ))}
        </div>
      </div>
    </section>
  );
};
