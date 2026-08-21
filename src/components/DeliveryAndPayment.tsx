import React from 'react';
import { MapPin, Navigation, ExternalLink, QrCode, CreditCard, ShieldCheck, Clock } from 'lucide-react';
import { GOOGLE_MAPS_URL, NEIGHBORHOODS, STORE_ADDRESS_OFFICIAL, STORE_REFERENCE } from '../data/products';
import { NeighborhoodDelivery, PaymentMethod } from '../types';

interface DeliveryAndPaymentProps {
  selectedNeighborhood: NeighborhoodDelivery;
  onSelectNeighborhood: (neighborhood: NeighborhoodDelivery) => void;
  onSelectPaymentAndOrder: (method: PaymentMethod) => void;
}

export const DeliveryAndPayment: React.FC<DeliveryAndPaymentProps> = ({
  selectedNeighborhood,
  onSelectNeighborhood,
  onSelectPaymentAndOrder,
}) => {
  return (
    <section className="py-10 bg-[#FAF0E6]/50 border-t border-b border-[#F4DEC9]">
      <div className="max-w-6xl mx-auto px-4">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8">
          {/* Left: Delivery 5km radius calculator */}
          <div className="lg:col-span-7 bg-white rounded-3xl p-6 border border-[#F4DEC9] shadow-sm flex flex-col justify-between">
            <div>
              <div className="flex items-center justify-between gap-2 mb-3">
                <div className="flex items-center gap-2">
                  <div className="w-8 h-8 rounded-xl bg-[#C25E38] flex items-center justify-center text-white">
                    <Navigation className="w-4 h-4" />
                  </div>
                  <div>
                    <h3 className="text-lg font-black text-[#4A2810]">
                      Raio de Entrega: 5.0 km
                    </h3>
                    <p className="text-xs text-[#8C5D3B]">
                      {STORE_ADDRESS_OFFICIAL} (Ref: {STORE_REFERENCE})
                    </p>
                  </div>
                </div>

                <a
                  href={GOOGLE_MAPS_URL}
                  target="_blank"
                  rel="noreferrer"
                  className="hidden sm:flex items-center gap-1 text-xs font-bold text-[#C25E38] hover:underline"
                >
                  Ver no Maps
                  <ExternalLink className="w-3.5 h-3.5" />
                </a>
              </div>

              <p className="text-xs text-[#6B4423] mb-4">
                Selecione seu bairro ou etapa abaixo para calcular a taxa de entrega e o tempo estimado até a sua casa:
              </p>

              {/* Neighborhoods list */}
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-2.5">
                {NEIGHBORHOODS.map((zone) => {
                  const isSelected = selectedNeighborhood.name === zone.name;
                  return (
                    <button
                      key={zone.name}
                      onClick={() => onSelectNeighborhood(zone)}
                      className={`p-3 rounded-2xl border text-left transition-all cursor-pointer flex items-center justify-between ${
                        isSelected
                          ? 'bg-[#FAF0E6] border-[#C25E38] ring-2 ring-[#C25E38]/20 shadow-xs'
                          : 'bg-white border-[#F4DEC9] hover:border-[#E5C3A6]'
                      }`}
                    >
                      <div>
                        <p className={`text-xs font-bold ${isSelected ? 'text-[#C25E38]' : 'text-[#4A2810]'}`}>
                          {zone.name}
                        </p>
                        <p className="text-[11px] text-[#8C5D3B]">
                          ~{zone.distanceKm} km • {zone.timeEstimate}
                        </p>
                      </div>
                      <span className="text-xs font-black text-[#C25E38] shrink-0 ml-2">
                        R$ {zone.fee.toFixed(2).replace('.', ',')}
                      </span>
                    </button>
                  );
                })}
              </div>
            </div>

            {/* Dynamic result strip */}
            <div className="mt-5 p-3.5 bg-[#FAF0E6] border border-[#E5C3A6] rounded-2xl flex flex-wrap items-center justify-between gap-3">
              <div className="flex items-center gap-2">
                <Clock className="w-4 h-4 text-[#C25E38]" />
                <span className="text-xs text-[#4A2810]">
                  Previsão para <strong>{selectedNeighborhood.name}</strong>: <strong>{selectedNeighborhood.timeEstimate}</strong>
                </span>
              </div>
              <div className="text-right">
                <span className="text-[10px] uppercase font-bold text-[#8C5D3B] block">Taxa de Entrega</span>
                <span className="text-sm font-black text-[#C25E38]">
                  R$ {selectedNeighborhood.fee.toFixed(2).replace('.', ',')}
                </span>
              </div>
            </div>
          </div>

          {/* Right: Payment Methods Card */}
          <div className="lg:col-span-5 bg-[#4A2810] text-white rounded-3xl p-6 shadow-md flex flex-col justify-between">
            <div>
              <div className="flex items-center gap-2 text-xs font-extrabold text-[#FDE68A] uppercase tracking-wider mb-1">
                <ShieldCheck className="w-4 h-4" />
                Formas de Pagamento
              </div>
              <h3 className="text-xl font-black text-white">
                Praticidade e Segurança
              </h3>
              <p className="text-xs text-[#FDE68A]/80 mt-1">
                Escolha sua forma preferida para abrir o pedido:
              </p>

              <div className="space-y-2.5 mt-5">
                {/* PIX */}
                <button
                  onClick={() => onSelectPaymentAndOrder('PIX')}
                  className="w-full bg-white/10 hover:bg-white/20 border border-white/15 p-3.5 rounded-2xl flex items-center justify-between text-left transition-all cursor-pointer group"
                >
                  <div className="flex items-center gap-3">
                    <div className="w-9 h-9 rounded-xl bg-[#00B4D8]/20 text-[#90E0EF] flex items-center justify-center">
                      <QrCode className="w-5 h-5" />
                    </div>
                    <div>
                      <div className="flex items-center gap-2">
                        <span className="text-xs font-bold text-white">PIX Instantâneo</span>
                        <span className="bg-[#C25E38] text-white text-[9px] font-bold px-2 py-0.5 rounded-md">
                          Mais Rápido
                        </span>
                      </div>
                      <p className="text-[11px] text-[#FDE68A]/70">Chave PIX direta com confirmação rápida</p>
                    </div>
                  </div>
                  <span className="text-xs font-bold text-[#FDE68A] group-hover:translate-x-1 transition-transform">→</span>
                </button>

                {/* Débito */}
                <button
                  onClick={() => onSelectPaymentAndOrder('DEBITO')}
                  className="w-full bg-white/10 hover:bg-white/20 border border-white/15 p-3.5 rounded-2xl flex items-center justify-between text-left transition-all cursor-pointer group"
                >
                  <div className="flex items-center gap-3">
                    <div className="w-9 h-9 rounded-xl bg-[#D97706]/20 text-[#FDE68A] flex items-center justify-center">
                      <CreditCard className="w-5 h-5" />
                    </div>
                    <div>
                      <div className="flex items-center gap-2">
                        <span className="text-xs font-bold text-white">Cartão de Débito</span>
                        <span className="bg-white/20 text-white text-[9px] font-bold px-2 py-0.5 rounded-md">
                          Maquininha na entrega
                        </span>
                      </div>
                      <p className="text-[11px] text-[#FDE68A]/70">O motoboy leva a maquininha sem fio até você</p>
                    </div>
                  </div>
                  <span className="text-xs font-bold text-[#FDE68A] group-hover:translate-x-1 transition-transform">→</span>
                </button>

                {/* Crédito */}
                <button
                  onClick={() => onSelectPaymentAndOrder('CREDITO')}
                  className="w-full bg-white/10 hover:bg-white/20 border border-white/15 p-3.5 rounded-2xl flex items-center justify-between text-left transition-all cursor-pointer group"
                >
                  <div className="flex items-center gap-3">
                    <div className="w-9 h-9 rounded-xl bg-[#E07A5F]/20 text-[#FFCDB2] flex items-center justify-center">
                      <CreditCard className="w-5 h-5" />
                    </div>
                    <div>
                      <div className="flex items-center gap-2">
                        <span className="text-xs font-bold text-white">Cartão de Crédito</span>
                        <span className="bg-white/20 text-white text-[9px] font-bold px-2 py-0.5 rounded-md">
                          Maquininha na entrega
                        </span>
                      </div>
                      <p className="text-[11px] text-[#FDE68A]/70">Visa, Master, Elo e principais bandeiras</p>
                    </div>
                  </div>
                  <span className="text-xs font-bold text-[#FDE68A] group-hover:translate-x-1 transition-transform">→</span>
                </button>
              </div>
            </div>

            <div className="mt-5 pt-4 border-t border-white/15 flex items-center gap-2 text-xs text-[#FDE68A]/90">
              <ShieldCheck className="w-4 h-4 text-[#25D366]" />
              <span>Sem taxas extras no cartão ou PIX. Pague ao receber!</span>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};
