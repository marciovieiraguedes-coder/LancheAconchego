import { NeighborhoodDelivery, Product } from '../types';

export const WHATSAPP_PHONE_NUMBER = '5592992367559';
export const WHATSAPP_DISPLAY_NUMBER = '(92) 99236-7559';
export const INSTAGRAM_URL = 'https://www.instagram.com/lanche.aconchego/#';
export const FACEBOOK_URL = 'https://www.facebook.com/professional_dashboard/?ref=profile_plus_left_nav';
export const INSTAGRAM_HANDLE = 'lanche.aconchego';
export const FACEBOOK_HANDLE = 'Lanche Aconchego';
export const GOOGLE_MAPS_URL = 'https://maps.app.goo.gl/iAS875jDwLdQK9ub8';

export const STORE_NAME = 'Lanche Aconchego';
export const STORE_ADDRESS_OFFICIAL = 'Rua Rio Dimiti, nº 26, Bairro São José Operário, 69086-051 - Manaus, AM';
export const STORE_REFERENCE = 'Campo do Bahia';
export const OPENING_HOURS = 'Terça a Domingo: 18:00 às 23:30 (Segunda: Fechado)';

export const FEATURED_CAROUSEL_PRODUCTS = [
  {
    id: 'feat-esfirras',
    name: 'Esfirras Artesanais',
    headline: 'Massa levinha e recheio farto',
    description: 'Abertas e fechadas com receitas caseiras de carne temperada, queijo cremoso e frango.',
    startingPrice: 5.90,
    priceLabel: 'A partir de R$ 5,90',
    image: 'https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?auto=format&fit=crop&w=800&q=80',
    badge: 'Mais Pedido ⭐',
    categoryTarget: 'Salgados' as const
  },
  {
    id: 'feat-pasteis',
    name: 'Pastéis Mega Crocantes',
    headline: 'Fritinhos na hora com massa sequinha',
    description: 'Casquinha dourada com bolhinhas estaladiças, recheios suculentos de carne, queijo e palmito.',
    startingPrice: 8.50,
    priceLabel: 'A partir de R$ 8,50',
    image: 'https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80',
    badge: 'Crocância Máxima 🔥',
    categoryTarget: 'Salgados' as const
  },
  {
    id: 'feat-doces',
    name: 'Doces & Sobremesas do Chefe',
    headline: 'Adoce seu momento de aconchego',
    description: 'Esfirras de Nutella com morango, pastéis doces com canela e o clássico pudim caseiro.',
    startingPrice: 7.50,
    priceLabel: 'A partir de R$ 7,50',
    image: 'https://images.unsplash.com/photo-1551024709-8f23befc6f87?auto=format&fit=crop&w=800&q=80',
    badge: 'Irresistível 🍫',
    categoryTarget: 'Doces' as const
  },
  {
    id: 'feat-sucos',
    name: 'Sucos Naturais & Bebidas Geladas',
    headline: '100% da fruta fresca para acompanhar',
    description: 'Sucos naturais de laranja, maracujá e refrigerantes servidos trincando de gelados.',
    startingPrice: 6.00,
    priceLabel: 'A partir de R$ 6,00',
    image: 'https://images.unsplash.com/photo-1613478223719-2ab802602423?auto=format&fit=crop&w=800&q=80',
    badge: 'Super Refrescante 🍊',
    categoryTarget: 'Bebidas' as const
  }
];

export const PRODUCTS: Product[] = [
  // SALGADOS
  {
    id: 'sal-1',
    name: 'Esfirra Tradicional de Carne',
    description: 'Carne bovina de primeira temperada com cebola fresca, tomate, hortelã e limão.',
    price: 5.90,
    startingFrom: true,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?auto=format&fit=crop&w=600&q=80',
    popular: true,
    badge: 'Destaque R$ 5,90'
  },
  {
    id: 'sal-2',
    name: 'Esfirra de Queijo com Orégano',
    description: 'Queijo derretido de alta cremosidade com um toque aromático de orégano especial.',
    price: 6.50,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&w=600&q=80',
    vegetarian: true
  },
  {
    id: 'sal-3',
    name: 'Esfirra de Frango com Catupiry',
    description: 'Frango desfiado suculento coberto com legítimo requeijão cremoso tipo Catupiry.',
    price: 6.90,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?auto=format&fit=crop&w=600&q=80',
    popular: true
  },
  {
    id: 'sal-4',
    name: 'Esfirra de Calabresa Especial',
    description: 'Calabresa moída artesanal levemente apimentada com cebola caramelizada e azeitonas.',
    price: 6.50,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1574484284002-952d92456975?auto=format&fit=crop&w=600&q=80'
  },
  {
    id: 'sal-5',
    name: 'Pastel Especial de Carne',
    description: 'Massa crocante estaladiça com farto recheio de carne moída temperada, azeitonas e ovos.',
    price: 8.50,
    startingFrom: true,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=600&q=80',
    popular: true,
    badge: 'Destaque R$ 8,50'
  },
  {
    id: 'sal-6',
    name: 'Pastel de Queijo com Tomate Seco',
    description: 'Mussarela derretida em abundância, tomate seco artesanal e manjericão fresco.',
    price: 8.90,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1608897013039-887f21d8c804?auto=format&fit=crop&w=600&q=80',
    vegetarian: true
  },
  {
    id: 'sal-7',
    name: 'Pastel de Palmito Cremoso',
    description: 'Palmito nobre picado envolto em molho branco aveludado e cheiro-verde.',
    price: 9.50,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1529042410759-befb1204b468?auto=format&fit=crop&w=600&q=80',
    vegetarian: true
  },
  {
    id: 'sal-8',
    name: 'Pastel de Frango com Catupiry e Bacon',
    description: 'Combinação perfeita de frango desfiado, cubinhos crocantes de bacon e Catupiry.',
    price: 9.90,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?auto=format&fit=crop&w=600&q=80',
    popular: true
  },
  {
    id: 'sal-9',
    name: 'Coxinha Dourada Aconchego',
    description: 'Massa de batata macia por dentro e ultra crocante por fora, frango desfiado bem temperadinho.',
    price: 7.50,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?auto=format&fit=crop&w=600&q=80',
    popular: true
  },
  {
    id: 'sal-10',
    name: 'Kibe Recheado com Catupiry',
    description: 'Trigo integral e carne com hortelã fresca, recheado com uma camada generosa de Catupiry.',
    price: 7.90,
    category: 'Salgados',
    image: 'https://images.unsplash.com/photo-1529042410759-befb1204b468?auto=format&fit=crop&w=600&q=80'
  },

  // DOCES
  {
    id: 'doc-1',
    name: 'Esfirra Doce de Nutella com Morango',
    description: 'Camada farta de Nutella pura finalizada com fatias frescas de morango selecionado.',
    price: 8.90,
    category: 'Doces',
    image: 'https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=600&q=80',
    popular: true,
    badge: 'Favorito'
  },
  {
    id: 'doc-2',
    name: 'Esfirra Doce de Leite com Coco Queimado',
    description: 'Doce de leite artesanal cremoso salpicado com flocos de coco tostado na hora.',
    price: 7.90,
    category: 'Doces',
    image: 'https://images.unsplash.com/photo-1587314168485-3236d6710814?auto=format&fit=crop&w=600&q=80'
  },
  {
    id: 'doc-3',
    name: 'Esfirra Romeu e Julieta',
    description: 'A clássica união brasileira de goiabada cascão derretida com queijo suave.',
    price: 7.50,
    category: 'Doces',
    image: 'https://images.unsplash.com/photo-1563729784474-d77dbb933a9e?auto=format&fit=crop&w=600&q=80',
    vegetarian: true
  },
  {
    id: 'doc-4',
    name: 'Pastel de Chocolate com Banana e Canela',
    description: 'Massa crocante recheada com chocolate ao leite derretido, banana fatiada e açúcar com canela.',
    price: 9.50,
    category: 'Doces',
    image: 'https://images.unsplash.com/photo-1551024709-8f23befc6f87?auto=format&fit=crop&w=600&q=80',
    popular: true
  },
  {
    id: 'doc-5',
    name: 'Churros Crocantes com Recheio Duplo',
    description: 'Porção com 4 mini churros douradinhos com calda de doce de leite e chocolate.',
    price: 8.50,
    category: 'Doces',
    image: 'https://images.unsplash.com/photo-1624353365286-3f8d62daad51?auto=format&fit=crop&w=600&q=80'
  },

  // BEBIDAS
  {
    id: 'beb-1',
    name: 'Suco Natural de Laranja 500ml',
    description: 'Feito na hora com laranjas frescas selecionadas, 100% puro e sem conservantes.',
    price: 8.00,
    category: 'Bebidas',
    image: 'https://images.unsplash.com/photo-1613478223719-2ab802602423?auto=format&fit=crop&w=600&q=80',
    popular: true
  },
  {
    id: 'beb-2',
    name: 'Suco Natural de Maracujá 500ml',
    description: 'Polpa natural da fruta batida com água mineral e gelo, super refrescante e calmante.',
    price: 8.50,
    category: 'Bebidas',
    image: 'https://images.unsplash.com/photo-1534353473418-4cfa6c56fd38?auto=format&fit=crop&w=600&q=80'
  },
  {
    id: 'beb-3',
    name: 'Refrigerante Lata 350ml',
    description: 'Lata bem geladinha. Escolha nas observações entre Coca-Cola, Coca Zero ou Guaraná Antarctica.',
    price: 6.00,
    category: 'Bebidas',
    image: 'https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&w=600&q=80'
  },
  {
    id: 'beb-4',
    name: 'Refrigerante 2 Litros (Família)',
    description: 'Ideal para compartilhar com a família e amigos no aconchego da sua casa.',
    price: 13.00,
    category: 'Bebidas',
    image: 'https://images.unsplash.com/photo-1581009146145-b5ef050c2e1e?auto=format&fit=crop&w=600&q=80'
  },
  {
    id: 'beb-5',
    name: 'Chá Gelado com Limão e Hortelã 500ml',
    description: 'Chá mate com limão espremido e folhas de hortelã fresca, servido com gelo.',
    price: 7.50,
    category: 'Bebidas',
    image: 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?auto=format&fit=crop&w=600&q=80'
  },
  {
    id: 'beb-6',
    name: 'Água Mineral 500ml',
    description: 'Água mineral da serra (com ou sem gás).',
    price: 4.00,
    category: 'Bebidas',
    image: 'https://images.unsplash.com/photo-1560023907-5f339617ea30?auto=format&fit=crop&w=600&q=80'
  },

  // SOBREMESAS
  {
    id: 'sob-1',
    name: 'Pudim de Leite Condensado da Vovó',
    description: 'Fatia generosa, sem furinhos, extremamente lisinho com calda de caramelo dourada.',
    price: 9.90,
    category: 'Sobremesas',
    image: 'https://images.unsplash.com/photo-1541781774459-bb2af2f05b55?auto=format&fit=crop&w=600&q=80',
    popular: true,
    badge: 'Receita de Família ❤️'
  },
  {
    id: 'sob-2',
    name: 'Bolo de Pote Cenoura com Brigadeiro',
    description: 'Massa fofinha de cenoura com camadas generosas de brigadeiro artesanal belga.',
    price: 11.50,
    category: 'Sobremesas',
    image: 'https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=600&q=80'
  },
  {
    id: 'sob-3',
    name: 'Torta Holandesa Artesanal',
    description: 'Creme suave com borda de biscoitos Calypso e cobertura espelhada de chocolate meio amargo.',
    price: 12.90,
    category: 'Sobremesas',
    image: 'https://images.unsplash.com/photo-1565958011703-44f9829ba187?auto=format&fit=crop&w=600&q=80'
  },
  {
    id: 'sob-4',
    name: 'Mousse de Maracujá com Calda',
    description: 'Textura aveludada e equilíbrio perfeito entre doce e azedinho natural.',
    price: 8.90,
    category: 'Sobremesas',
    image: 'https://images.unsplash.com/photo-1587314168485-3236d6710814?auto=format&fit=crop&w=600&q=80'
  }
];

export const NEIGHBORHOODS: NeighborhoodDelivery[] = [
  {
    name: 'São José Operário (Próx. Campo do Bahia)',
    distanceKm: 0.6,
    fee: 3.50,
    timeEstimate: '20-30 min'
  },
  {
    name: 'São José (Etapas 1, 2 e 3)',
    distanceKm: 1.5,
    fee: 4.50,
    timeEstimate: '25-35 min'
  },
  {
    name: 'Zumbi dos Palmares',
    distanceKm: 2.2,
    fee: 5.00,
    timeEstimate: '30-40 min'
  },
  {
    name: 'Tancredo Neves & Castanheiras',
    distanceKm: 3.1,
    fee: 6.00,
    timeEstimate: '30-45 min'
  },
  {
    name: 'Armando Mendes & Grande Vitória',
    distanceKm: 3.8,
    fee: 7.00,
    timeEstimate: '35-50 min'
  },
  {
    name: 'Nova Floresta & Jorge Teixeira (Até 5km)',
    distanceKm: 4.8,
    fee: 8.00,
    timeEstimate: '40-50 min'
  }
];
