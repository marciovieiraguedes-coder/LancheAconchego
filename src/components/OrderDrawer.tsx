import React from 'react';
import { X, Plus, Minus, Trash2, ShoppingBag, MapPin, Phone, User, Send, QrCode, CreditCard, Sparkles } from 'lucide-react';
import { NEIGHBORHOODS, STORE_ADDRESS_OFFICIAL, WHATSAPP_PHONE_NUMBER } from '../data/products';
import { CartItem, CustomerOrderData, NeighborhoodDelivery, PaymentMethod } from '../types';
import confetti from 'canvas-confetti';

interface OrderDrawerProps {
  isOpen: boolean;
  cartItems: Record<string, CartItem>;
  subtotal: number;
  deliveryFee: number;
  grandTotal: number;
  selectedNeighborhood: NeighborhoodDelivery;
  customerData: CustomerOrderData;
  onClose: () => void;
  onUpdateQuantity: (productId: string, delta: number) => void;
  onRemoveItem: (productId: string) => void;
  onClearCart: () => void;
  onSelectNeighborhood: (neighborhood: NeighborhoodDelivery) => void;
  onSelectPaymentMethod: (method: PaymentMethod) => void;
  onUpdateCustomerData: (data: Partial<CustomerOrderData>) => void;
}

export const OrderDrawer: React.FC<OrderDrawerProps> = ({
  isOpen,
  cartItems,
  subtotal,
  deliveryFee,
  grandTotal,
  selectedNeighborhood,
  customerData,
  onClose,
  onUpdateQuantity,
  onRemoveItem,
  onClearCart,
  onSelectNeighborhood,
  onSelectPaymentMethod,
  onUpdateCustomerData,
}) => {
  if (!isOpen) return null;

  const itemsList: CartItem[] = Object.values(cartItems);

  const handleSubmitOrder = (e: React.FormEvent) => {
    e.preventDefault();

    if (itemsList.length === 0) return;

    // Trigger confetti
    try {
      confetti({
        particleCount: 80,
        spread: 70,
        origin: { y: 0.6 },
      });
    } catch (_) {}

    const itemsText = itemsList
      .map(
        (item) =>
          `• *${item.quantity}x* ${item.product.name} - R$ ${(item.product.price * item.quantity).toFixed(2).replace('.', ',')}${
            item.notes ? ` _(Obs: ${item.notes})_` : ''
          }`
      )
      .join('\n');

    const paymentLabel = {
      PIX: 'PIX (Chave Instantânea)',
      DEBITO: 'Cartão de Débito 💳 (Levar maquininha na entrega)',
      CREDITO: 'Cartão de Crédito 💳 (Levar maquininha na entrega)',
    }[customerData.paymentMethod];

    const changeText =
      customerData.needsChange && customerData.changeAmount
        ? `\n*Troco para:* R$ ${customerData.changeAmount}`
        : '';

    const message = `🥟 *NOVO PEDIDO - LANCHE ACONCHEGO*
_Endereço Oficial: ${STORE_ADDRESS_OFFICIAL}_
----------------------------------------
*Cliente:* ${customerData.customerName || 'Cliente Aconchego'}
*Telefone:* ${customerData.phone || 'Não informado'}
*Bairro/Região (Manaus - Raio 5km):* ${selectedNeighborhood.name}
*Endereço Completo:* ${customerData.address || 'Retirada ou combinar'}
*Ponto de Referência:* ${customerData.referencePoint || 'Sem referência'}

*📋 ITENS DO PEDIDO:*
${itemsText}

----------------------------------------
*Subtotal:* R$ ${subtotal.toFixed(2).replace('.', ',')}
*Taxa de Entrega:* R$ ${deliveryFee.toFixed(2).replace('.', ',')} (${selectedNeighborhood.name})
*TOTAL A PAGAR:* R$ ${grandTotal.toFixed(2).replace('.', ',')}

*Forma de Pagamento:* ${paymentLabel}${changeText}
*Tempo de Entrega Estimado:* ${selectedNeighborhood.timeEstimate}
----------------------------------------
_Pedido gerado via Cardápio Digital - Lanche Aconchego_`;

    const encoded = encodeURIComponent(message);
    const url = `https://api.whatsapp.com/send?phone=${WHATSAPP_PHONE_NUMBER}&text=${encoded}`;
    window.open(url, '_blank');
  };

  return (
    <div className="fixed inset-0 z-50 overflow-hidden">
      {/* Backdrop */}
      <div
        className="absolute inset-0 bg-black/50 backdrop-blur-xs transition-opacity"
        onClick={onClose}
      />

      <div className="fixed inset-y-0 right-0 max-w-full flex pl-10">
        <div className="w-screen max-w-md bg-[#FFFBF5] shadow-2xl flex flex-col justify-between">
          {/* Header */}
          <div className="bg-[#4A2810] text-white p-4 flex items-center justify-between">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-xl bg-[#C25E38] flex items-center justify-center text-white">
                <ShoppingBag className="w-5 h-5" />
              </div>
              <div>
                <h3 className="font-black text-base text-white">Seu Pedido Aconchego</h3>
                <p className="text-xs text-[#FDE68A]">
                  {itemsList.reduce((acc, i) => acc + i.quantity, 0)} itens no carrinho
                </p>
              </div>
            </div>

            <button
              onClick={onClose}
              className="w-8 h-8 rounded-full bg-white/10 hover:bg-white/20 flex items-center justify-center text-white transition-colors cursor-pointer"
            >
              <X className="w-5 h-5" />
            </button>
          </div>

          {/* Body Scrollable */}
          <div className="flex-1 overflow-y-auto p-4 space-y-5">
            {itemsList.length === 0 ? (
              <div className="py-12 text-center">
                <span className="text-5xl">🛒</span>
                <h4 className="text-base font-bold text-[#4A2810] mt-3">Seu carrinho está vazio</h4>
                <p className="text-xs text-[#6B4423] mt-1">
                  Selecione suas esfirras, pastéis e bebidas favoritas no cardápio.
                </p>
              </div>
            ) : (
              <>
                {/* Items summary */}
                <div>
                  <div className="flex items-center justify-between mb-2">
                    <span className="text-[11px] font-extrabold uppercase text-[#8C5D3B]">Itens Escolhidos</span>
                    <button
                      onClick={onClearCart}
                      className="text-xs font-bold text-[#C25E38] hover:underline flex items-center gap-1 cursor-pointer"
                    >
                      <Trash2 className="w-3.5 h-3.5" />
                      Limpar
                    </button>
                  </div>

                  <div className="space-y-2">
                    {itemsList.map((item) => (
                      <div
                        key={item.product.id}
                        className="bg-white p-3 rounded-2xl border border-[#F4DEC9] flex items-center justify-between gap-3 shadow-2xs"
                      >
                        <div className="flex-1 min-w-0">
                          <p className="text-xs font-bold text-[#4A2810] truncate">
                            {item.product.name}
                          </p>
                          <p className="text-[11px] text-[#8C5D3B]">
                            R$ {item.product.price.toFixed(2).replace('.', ',')} un.
                          </p>
                          {item.notes && (
                            <p className="text-[10px] text-[#C25E38] font-medium truncate">
                              Obs: {item.notes}
                            </p>
                          )}
                        </div>

                        <div className="flex items-center gap-2">
                          <div className="flex items-center bg-[#FAF0E6] border border-[#E5C3A6] rounded-xl p-0.5">
                            <button
                              onClick={() => onUpdateQuantity(item.product.id, -1)}
                              className="w-6 h-6 rounded-lg bg-white flex items-center justify-center text-[#4A2810] hover:bg-[#F4DEC9] text-xs cursor-pointer"
                            >
                              <Minus className="w-3 h-3" />
                            </button>
                            <span className="w-6 text-center font-black text-xs text-[#4A2810]">
                              {item.quantity}
                            </span>
                            <button
                              onClick={() => onUpdateQuantity(item.product.id, 1)}
                              className="w-6 h-6 rounded-lg bg-[#C25E38] text-white flex items-center justify-center text-xs cursor-pointer"
                            >
                              <Plus className="w-3 h-3" />
                            </button>
                          </div>

                          <p className="text-xs font-black text-[#C25E38] w-14 text-right">
                            R$ {(item.product.price * item.quantity).toFixed(2).replace('.', ',')}
                          </p>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>

                {/* Form fields */}
                <form id="checkout-form" onSubmit={handleSubmitOrder} className="space-y-3.5 pt-2 border-t border-[#F4DEC9]">
                  <span className="text-[11px] font-extrabold uppercase text-[#8C5D3B] block">
                    Dados de Entrega (Manaus - Raio 5km)
                  </span>

                  <div>
                    <label className="text-xs font-bold text-[#4A2810] block mb-1">Seu Nome</label>
                    <div className="relative">
                      <User className="w-4 h-4 text-[#8C5D3B] absolute left-3 top-1/2 -translate-y-1/2" />
                      <input
                        type="text"
                        required
                        value={customerData.customerName}
                        onChange={(e) => onUpdateCustomerData({ customerName: e.target.value })}
                        placeholder="Ex: Carlos Silva"
                        className="w-full pl-9 pr-3 py-2 text-xs bg-white border border-[#E5C3A6] focus:border-[#C25E38] rounded-xl text-[#4A2810] outline-none"
                      />
                    </div>
                  </div>

                  <div>
                    <label className="text-xs font-bold text-[#4A2810] block mb-1">WhatsApp para Contato</label>
                    <div className="relative">
                      <Phone className="w-4 h-4 text-[#8C5D3B] absolute left-3 top-1/2 -translate-y-1/2" />
                      <input
                        type="tel"
                        required
                        value={customerData.phone}
                        onChange={(e) => onUpdateCustomerData({ phone: e.target.value })}
                        placeholder="Ex: (92) 99999-9999"
                        className="w-full pl-9 pr-3 py-2 text-xs bg-white border border-[#E5C3A6] focus:border-[#C25E38] rounded-xl text-[#4A2810] outline-none"
                      />
                    </div>
                  </div>

                  <div>
                    <label className="text-xs font-bold text-[#4A2810] block mb-1">Bairro / Região</label>
                    <select
                      value={selectedNeighborhood.name}
                      onChange={(e) => {
                        const found = NEIGHBORHOODS.find((n) => n.name === e.target.value);
                        if (found) onSelectNeighborhood(found);
                      }}
                      className="w-full px-3 py-2 text-xs bg-white border border-[#E5C3A6] focus:border-[#C25E38] rounded-xl text-[#4A2810] outline-none cursor-pointer"
                    >
                      {NEIGHBORHOODS.map((n) => (
                        <option key={n.name} value={n.name}>
                          {n.name} (+ R$ {n.fee.toFixed(2).replace('.', ',')})
                        </option>
                      ))}
                    </select>
                  </div>

                  <div>
                    <label className="text-xs font-bold text-[#4A2810] block mb-1">Endereço Completo</label>
                    <div className="relative">
                      <MapPin className="w-4 h-4 text-[#8C5D3B] absolute left-3 top-1/2 -translate-y-1/2" />
                      <input
                        type="text"
                        required
                        value={customerData.address}
                        onChange={(e) => onUpdateCustomerData({ address: e.target.value })}
                        placeholder="Rua, Número, Bloco / Apto"
                        className="w-full pl-9 pr-3 py-2 text-xs bg-white border border-[#E5C3A6] focus:border-[#C25E38] rounded-xl text-[#4A2810] outline-none"
                      />
                    </div>
                  </div>

                  <div>
                    <label className="text-xs font-bold text-[#4A2810] block mb-1">Ponto de Referência</label>
                    <input
                      type="text"
                      value={customerData.referencePoint}
                      onChange={(e) => onUpdateCustomerData({ referencePoint: e.target.value })}
                      placeholder="Ex: Próximo à praça / em frente ao mercadinho"
                      className="w-full px-3 py-2 text-xs bg-white border border-[#E5C3A6] focus:border-[#C25E38] rounded-xl text-[#4A2810] outline-none"
                    />
                  </div>

                  {/* Payment method selection */}
                  <div className="pt-2">
                    <label className="text-xs font-bold text-[#4A2810] block mb-1.5">Forma de Pagamento</label>
                    <div className="grid grid-cols-3 gap-2">
                      {(['PIX', 'DEBITO', 'CREDITO'] as PaymentMethod[]).map((method) => {
                        const isSelected = customerData.paymentMethod === method;
                        return (
                          <button
                            key={method}
                            type="button"
                            onClick={() => onSelectPaymentMethod(method)}
                            className={`py-2 px-1.5 rounded-xl border text-center transition-all cursor-pointer ${
                              isSelected
                                ? 'bg-[#C25E38] border-[#C25E38] text-white font-bold shadow-xs'
                                : 'bg-white border-[#E5C3A6] text-[#4A2810] hover:bg-[#FAF0E6]'
                            }`}
                          >
                            <p className="text-xs leading-none">
                              {method === 'PIX' ? 'PIX' : method === 'DEBITO' ? 'Débito' : 'Crédito'}
                            </p>
                          </button>
                        );
                      })}
                    </div>
                  </div>

                  {/* Change option */}
                  <div className="flex items-center justify-between pt-1">
                    <label className="text-xs text-[#4A2810] flex items-center gap-2 cursor-pointer">
                      <input
                        type="checkbox"
                        checked={customerData.needsChange}
                        onChange={(e) => onUpdateCustomerData({ needsChange: e.target.checked })}
                        className="rounded text-[#C25E38] focus:ring-[#C25E38]"
                      />
                      Precisa de troco em dinheiro?
                    </label>

                    {customerData.needsChange && (
                      <input
                        type="text"
                        value={customerData.changeAmount}
                        onChange={(e) => onUpdateCustomerData({ changeAmount: e.target.value })}
                        placeholder="Troco p/ R$ 50,00"
                        className="w-32 px-2.5 py-1 text-xs bg-white border border-[#E5C3A6] rounded-xl text-[#4A2810] outline-none"
                      />
                    )}
                  </div>
                </form>
              </>
            )}
          </div>

          {/* Footer & Grand Total */}
          {itemsList.length > 0 && (
            <div className="p-4 bg-white border-t border-[#F4DEC9] shadow-lg space-y-3">
              {/* Summary */}
              <div className="space-y-1.5 text-xs">
                <div className="flex justify-between text-[#6B4423]">
                  <span>Subtotal itens:</span>
                  <span className="font-bold">R$ {subtotal.toFixed(2).replace('.', ',')}</span>
                </div>
                <div className="flex justify-between text-[#6B4423]">
                  <span>Taxa de entrega ({selectedNeighborhood.name}):</span>
                  <span className="font-bold text-[#C25E38]">
                    + R$ {deliveryFee.toFixed(2).replace('.', ',')}
                  </span>
                </div>
                <div className="flex justify-between text-sm font-black text-[#4A2810] pt-1.5 border-t border-[#F4DEC9]">
                  <span>TOTAL GERAL:</span>
                  <span className="text-[#C25E38] text-base">
                    R$ {grandTotal.toFixed(2).replace('.', ',')}
                  </span>
                </div>
              </div>

              {/* Submit button */}
              <button
                type="submit"
                form="checkout-form"
                className="w-full bg-[#25D366] hover:bg-[#1EBE5D] text-white font-black text-sm py-3.5 px-4 rounded-2xl flex items-center justify-center gap-2 shadow-md transition-all active:scale-98 cursor-pointer"
              >
                <span>💬</span>
                Finalizar Pedido via WhatsApp
              </button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
