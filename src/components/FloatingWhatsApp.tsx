import React, { useState } from 'react';
import { MessageCircle, X, Send, MapPin } from 'lucide-react';
import { STORE_ADDRESS_OFFICIAL, WHATSAPP_PHONE_NUMBER } from '../data/products';

interface FloatingWhatsAppProps {
  cartCount: number;
}

export const FloatingWhatsApp: React.FC<FloatingWhatsAppProps> = ({ cartCount }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [customMsg, setCustomMsg] = useState('');

  const handleSend = (text: string) => {
    const message = text || 'Olá! Gostaria de fazer um pedido no Lanche Aconchego e tirar algumas dúvidas.';
    const encoded = encodeURIComponent(message);
    window.open(`https://api.whatsapp.com/send?phone=${WHATSAPP_PHONE_NUMBER}&text=${encoded}`, '_blank');
    setIsOpen(false);
  };

  return (
    <div className="fixed bottom-6 right-6 z-40">
      {/* Quick Modal Popup */}
      {isOpen && (
        <div className="mb-3 w-80 bg-white rounded-3xl border border-[#F4DEC9] shadow-2xl overflow-hidden animate-in fade-in slide-in-from-bottom-5">
          <div className="bg-gradient-to-r from-[#1EBE5D] to-[#25D366] p-4 text-white flex items-center justify-between">
            <div className="flex items-center gap-2.5">
              <div className="w-8 h-8 rounded-full bg-white text-[#25D366] flex items-center justify-center text-lg font-bold">
                🥟
              </div>
              <div>
                <h4 className="text-xs font-bold leading-none">Lanche Aconchego</h4>
                <p className="text-[10px] text-white/80 mt-0.5">Atendimento WhatsApp Manaus</p>
              </div>
            </div>
            <button
              onClick={() => setIsOpen(false)}
              className="text-white/80 hover:text-white cursor-pointer"
            >
              <X className="w-4 h-4" />
            </button>
          </div>

          <div className="p-3.5 space-y-3">
            <div className="bg-[#FAF0E6] p-2.5 rounded-xl border border-[#F4DEC9] flex items-center gap-2 text-[11px] text-[#4A2810]">
              <MapPin className="w-3.5 h-3.5 text-[#C25E38] shrink-0" />
              <span>Rua Rio Dimiti, 26 • Campo do Bahia</span>
            </div>

            <p className="text-xs text-[#6B4423]">
              Como podemos ajudar você hoje no seu pedido de esfirras e pastéis?
            </p>

            <div className="grid grid-cols-2 gap-2">
              <button
                onClick={() => handleSend('Olá! Gostaria de consultar o tempo de entrega para o meu bairro.')}
                className="p-2 text-left bg-[#FAF0E6] hover:bg-[#F4DEC9] text-[#4A2810] text-[10px] font-bold rounded-xl border border-[#E5C3A6] transition-colors cursor-pointer"
              >
                Tempo de entrega?
              </button>
              <button
                onClick={() => handleSend('Olá! Gostaria de saber os sabores do cardápio disponíveis hoje.')}
                className="p-2 text-left bg-[#FAF0E6] hover:bg-[#F4DEC9] text-[#4A2810] text-[10px] font-bold rounded-xl border border-[#E5C3A6] transition-colors cursor-pointer"
              >
                Cardápio do dia
              </button>
            </div>

            <div className="space-y-2">
              <input
                type="text"
                value={customMsg}
                onChange={(e) => setCustomMsg(e.target.value)}
                placeholder="Digite sua dúvida ou mensagem..."
                className="w-full text-xs px-3 py-2 bg-white border border-[#E5C3A6] focus:border-[#25D366] rounded-xl outline-none"
              />

              <button
                onClick={() => handleSend(customMsg)}
                className="w-full bg-[#25D366] hover:bg-[#1EBE5D] text-white text-xs font-bold py-2.5 rounded-xl flex items-center justify-center gap-1.5 shadow-sm transition-all cursor-pointer"
              >
                <Send className="w-3.5 h-3.5" />
                Iniciar Conversa
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Round FAB */}
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="w-14 h-14 rounded-full bg-[#25D366] hover:bg-[#1EBE5D] text-white shadow-xl flex items-center justify-center relative hover:scale-110 active:scale-95 transition-all cursor-pointer"
        aria-label="Abrir WhatsApp"
      >
        <span className="text-2xl">💬</span>
        {cartCount > 0 && (
          <span className="absolute -top-1 -right-1 bg-[#C25E38] text-white text-[10px] font-bold w-5 h-5 rounded-full flex items-center justify-center border-2 border-white animate-pulse">
            {cartCount}
          </span>
        )}
      </button>
    </div>
  );
};
