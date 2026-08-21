export type ProductCategory = 'Todos' | 'Salgados' | 'Doces' | 'Bebidas' | 'Sobremesas';

export interface Product {
  id: string;
  name: string;
  description: string;
  price: number;
  startingFrom?: boolean;
  category: 'Salgados' | 'Doces' | 'Bebidas' | 'Sobremesas';
  image: string;
  badge?: string;
  popular?: boolean;
  vegetarian?: boolean;
  highlight?: boolean;
  serves?: string;
}

export interface CartItem {
  product: Product;
  quantity: number;
  notes?: string;
}

export type PaymentMethod = 'PIX' | 'DEBITO' | 'CREDITO';

export interface NeighborhoodDelivery {
  name: string;
  distanceKm: number;
  fee: number;
  timeEstimate: string;
}

export interface CustomerOrderData {
  customerName: string;
  phone: string;
  address: string;
  neighborhood: string;
  referencePoint: string;
  paymentMethod: PaymentMethod;
  needsChange: boolean;
  changeAmount: string;
  generalNotes?: string;
}
