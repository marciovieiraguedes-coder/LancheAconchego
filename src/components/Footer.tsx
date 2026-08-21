import React from 'react';
import { MapPin, Clock, Phone, Heart, ExternalLink } from 'lucide-react';
import { Logo } from './Logo';
import {
  FACEBOOK_HANDLE,
  FACEBOOK_URL,
  GOOGLE_MAPS_URL,
  INSTAGRAM_HANDLE,
  INSTAGRAM_URL,
  OPENING_HOURS,
  STORE_ADDRESS_OFFICIAL,
  STORE_REFERENCE,
  WHATSAPP_DISPLAY_NUMBER,
  WHATSAPP_PHONE_NUMBER
} from '../data/products';

export const Footer: React.FC = () => {
  return (
    <footer className="bg-[#4A2810] text-[#FFFBF5] pt-12 pb-8 border-t border-[#3D200E]">
      <div className="max-w-6xl mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-12 gap-8 pb-10 border-b border-white/10">
          {/* Brand and story */}
          <div className="md:col-span-5 space-y-4">
            <Logo size="lg" isDark={true} />
            <p className="text-xs sm:text-sm text-[#FDE68A]/80 leading-relaxed max-w-sm">
              Nascido no coração do bairro São José Operário, em Manaus, com receitas artesanais preparadas com muito carinho, ingredientes frescos e aquele aconchego que você só encontra aqui.
            </p>
            <div className="flex items-center gap-3 pt-1">
              <a
                href={INSTAGRAM_URL}
                target="_blank"
                rel="noreferrer"
                className="flex items-center gap-1.5 bg-white/10 hover:bg-white/20 border border-white/15 px-3 py-1.5 rounded-xl text-xs font-semibold text-white transition-colors"
              >
                <span>📸</span>
                <span>@{INSTAGRAM_HANDLE}</span>
              </a>
              <a
                href={FACEBOOK_URL}
                target="_blank"
                rel="noreferrer"
                className="flex items-center gap-1.5 bg-white/10 hover:bg-white/20 border border-white/15 px-3 py-1.5 rounded-xl text-xs font-semibold text-white transition-colors"
              >
                <span>👍</span>
                <span>{FACEBOOK_HANDLE}</span>
              </a>
            </div>
          </div>

          {/* Manaus 5km Radar Area Graphic */}
          <div className="md:col-span-4 bg-[#3D200E] p-4 rounded-3xl border border-white/10 flex flex-col justify-between">
            <div>
              <div className="flex items-center justify-between gap-2 mb-2">
                <span className="text-[10px] font-extrabold uppercase tracking-wider text-[#FDE68A]">
                  Área de Atendimento
                </span>
                <span className="bg-[#C25E38] text-white text-[9px] font-bold px-2 py-0.5 rounded-md">
                  MANAUS - AM
                </span>
              </div>
              <p className="text-xs font-bold text-white">Raio de Cobertura de até 5.0 km</p>
              <p className="text-[11px] text-[#FDE68A]/70 mt-1">
                São José (todas etapas), Zumbi, Tancredo Neves, Castanheiras, Armando Mendes, Grande Vitória e Jorge Teixeira.
              </p>
            </div>

            <div className="mt-4 pt-3 border-t border-white/10">
              <a
                href={GOOGLE_MAPS_URL}
                target="_blank"
                rel="noreferrer"
                className="flex items-center justify-center gap-1.5 w-full bg-[#C25E38] hover:bg-[#A84F2E] text-white text-xs font-bold py-2 rounded-xl transition-colors"
              >
                <MapPin className="w-3.5 h-3.5" />
                Ver Rota no Google Maps
                <ExternalLink className="w-3 h-3" />
              </a>
            </div>
          </div>

          {/* Contact and address */}
          <div className="md:col-span-3 space-y-3">
            <h4 className="text-xs font-extrabold uppercase tracking-wider text-[#FDE68A]">
              Informações Oficiais
            </h4>

            <div className="space-y-2.5 text-xs text-[#FDE68A]/90">
              <div className="flex items-start gap-2">
                <MapPin className="w-4 h-4 text-[#E07A5F] shrink-0 mt-0.5" />
                <div>
                  <p className="font-bold text-white">Endereço:</p>
                  <p className="text-[11px] leading-tight text-[#FDE68A]/80">{STORE_ADDRESS_OFFICIAL}</p>
                  <p className="text-[10px] text-[#C25E38] font-bold mt-0.5">Ref: {STORE_REFERENCE}</p>
                </div>
              </div>

              <div className="flex items-start gap-2">
                <Clock className="w-4 h-4 text-[#FDE68A] shrink-0 mt-0.5" />
                <div>
                  <p className="font-bold text-white">Horário:</p>
                  <p className="text-[11px] leading-tight text-[#FDE68A]/80">{OPENING_HOURS}</p>
                </div>
              </div>

              <div className="flex items-center gap-2">
                <Phone className="w-4 h-4 text-[#25D366] shrink-0" />
                <div>
                  <a
                    href={`https://wa.me/${WHATSAPP_PHONE_NUMBER}`}
                    target="_blank"
                    rel="noreferrer"
                    className="font-bold text-[#25D366] hover:underline"
                  >
                    {WHATSAPP_DISPLAY_NUMBER}
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Bottom copyright */}
        <div className="pt-6 flex flex-col sm:flex-row items-center justify-between gap-3 text-xs text-[#FDE68A]/60">
          <p>© {new Date().getFullYear()} Lanche Aconchego. Todos os direitos reservados. Manaus - AM.</p>
          <p className="flex items-center gap-1">
            Feito com <Heart className="w-3.5 h-3.5 text-[#C25E38] fill-[#C25E38]" /> em Manaus
          </p>
        </div>
      </div>
    </footer>
  );
};
