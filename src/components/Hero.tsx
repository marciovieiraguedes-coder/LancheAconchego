import React from 'react';
import { ArrowRight, Heart, MapPin, Star, Utensils } from 'lucide-react';
import { GOOGLE_MAPS_URL } from '../data/products';

interface HeroProps {
  onOrderNow: () => void;
  onViewMenu: () => void;
}

export const Hero: React.FC<HeroProps> = ({ onOrderNow, onViewMenu }) => {
  return (
    <section className="bg-gradient-to-b from-[#FFFBF5] via-[#FFF3E3] to-[#FFFBF5] py-8 sm:py-12 border-b border-[#F4DEC9]">
      <div className="max-w-6xl mx-auto px-4">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-center">
          {/* Text content */}
          <div className="lg:col-span-7 space-y-5 text-center lg:text-left">
            <div className="inline-flex items-center gap-2 bg-[#FAF0E6] border border-[#F4DEC9] px-3.5 py-1.5 rounded-full text-xs font-semibold text-[#8C5D3B]">
              <span className="w-2 h-2 rounded-full bg-[#C25E38] animate-ping" />
              <Heart className="w-3.5 h-3.5 text-[#C25E38] fill-[#C25E38]" />
              Feito com amor, quentinho e artesanal
            </div>

            <h1 className="text-3xl sm:text-4xl md:text-5xl font-black text-[#4A2810] tracking-tight leading-[1.15]">
              O Sabor do Aconchego direto na sua casa
            </h1>

            <p className="text-sm sm:text-base text-[#6B4423] leading-relaxed max-w-xl mx-auto lg:mx-0">
              Massa artesanal fofinha, pastéis crocantes sequinhos e esfirras recheadas com tempero de família. Saindo quentinho de São José Operário para toda a Zona Leste de Manaus.
            </p>

            {/* Price Anchors */}
            <div className="grid grid-cols-2 gap-3 max-w-md mx-auto lg:mx-0">
              <div className="bg-[#FAF0E6] border border-[#F4DEC9] rounded-2xl p-3.5 flex items-center gap-3">
                <div className="w-10 h-10 rounded-xl bg-[#C25E38]/10 text-xl flex items-center justify-center">
                  🥟
                </div>
                <div>
                  <p className="text-[10px] uppercase font-bold text-[#8C5D3B]">Esfirras</p>
                  <p className="text-xs text-[#6B4423]">A partir de</p>
                  <p className="text-base font-black text-[#C25E38]">R$ 5,90</p>
                </div>
              </div>

              <div className="bg-[#FAF0E6] border border-[#F4DEC9] rounded-2xl p-3.5 flex items-center gap-3">
                <div className="w-10 h-10 rounded-xl bg-[#D97706]/10 text-xl flex items-center justify-center">
                  🥐
                </div>
                <div>
                  <p className="text-[10px] uppercase font-bold text-[#8C5D3B]">Pastéis</p>
                  <p className="text-xs text-[#6B4423]">A partir de</p>
                  <p className="text-base font-black text-[#C25E38]">R$ 8,50</p>
                </div>
              </div>
            </div>

            {/* Action Buttons */}
            <div className="flex flex-wrap items-center justify-center lg:justify-start gap-3 pt-2">
              <button
                onClick={onOrderNow}
                className="flex items-center gap-2 bg-[#C25E38] hover:bg-[#A84F2E] text-white font-bold text-sm px-6 py-3.5 rounded-2xl shadow-lg shadow-[#C25E38]/20 transition-all hover:scale-105 active:scale-95 cursor-pointer"
              >
                Pedir agora
                <ArrowRight className="w-4 h-4" />
              </button>

              <button
                onClick={onViewMenu}
                className="flex items-center gap-2 bg-[#FAF0E6] hover:bg-[#F4DEC9] text-[#4A2810] border border-[#E5C3A6] font-bold text-sm px-6 py-3.5 rounded-2xl transition-all cursor-pointer"
              >
                <Utensils className="w-4 h-4 text-[#C25E38]" />
                Ver Cardápio
              </button>
            </div>

            {/* Micro badges */}
            <div className="flex flex-wrap items-center justify-center lg:justify-start gap-4 pt-2 text-xs text-[#6B4423]">
              <a
                href={GOOGLE_MAPS_URL}
                target="_blank"
                rel="noreferrer"
                className="flex items-center gap-1.5 hover:text-[#C25E38] transition-colors"
              >
                <MapPin className="w-3.5 h-3.5 text-[#C25E38]" />
                <span>Raio de 5km (Campo do Bahia, Manaus)</span>
              </a>
              <span className="flex items-center gap-1 font-semibold text-[#D97706]">
                <Star className="w-3.5 h-3.5 fill-[#D97706]" />
                4.9 ★ (+1.200 pedidos entregues)
              </span>
            </div>
          </div>

          {/* Hero Visual Card */}
          <div className="lg:col-span-5">
            <div className="relative mx-auto max-w-md lg:max-w-none">
              {/* Decorative blob */}
              <div className="absolute -inset-2 bg-gradient-to-tr from-[#C25E38]/30 via-[#FDE68A]/40 to-[#E07A5F]/30 rounded-3xl filter blur-xl opacity-70" />

              <div className="relative bg-white rounded-3xl overflow-hidden shadow-xl border border-[#F4DEC9]">
                <img
                  src="https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?auto=format&fit=crop&w=800&q=80"
                  alt="Esfirras e Salgados Artesanais Lanche Aconchego"
                  className="w-full h-64 sm:h-72 object-cover"
                />
                <div className="p-5 bg-gradient-to-t from-[#4A2810] via-[#4A2810]/95 to-[#4A2810]/80 text-white">
                  <span className="bg-[#E07A5F] text-white text-[10px] font-bold px-2.5 py-1 rounded-md uppercase tracking-wider">
                    Receita de Família
                  </span>
                  <h3 className="text-lg font-bold text-white mt-1.5">
                    Esfirras & Pastéis Quentinhos
                  </h3>
                  <p className="text-xs text-[#FDE68A]/90 mt-1">
                    Ingredientes nobres, queijo derretido e temperos frescos preparados na hora do seu pedido.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
